# YowYob Search PWA — Infrastructure  
Docker -  Kubernetes -  Terraform -  CI/CD -  Monitoring -  IaC

[![Kubernetes](https://img.shields.io/badge/Kubernetes-✓httpsker](https://img.shields.io/badge/Docker-✓-2496ED.svghttpsraform](https://img.shields.io/badge/Terraform-✓-purple.svghttpsmetheus](https://img.shields.io/badge/Prometheus-✓-orange.svghttpsObjectif

Ce dépôt contient **toute l'infrastructure nécessaire** pour exécuter YowYob Search en développement local et production :

- **Docker Compose** (développement local complet)
- **Kubernetes manifests** (staging/production)
- **Terraform** (Infrastructure as Code - GKE/CloudSQL)
- **CI/CD** (GitHub Actions + ArgoCD)
- **Monitoring** (Prometheus/Grafana/Loki/Tempo)
- **Reverse proxy/Ingress** (NGINX Ingress Controller)
- **Bases de données** (PostgreSQL/PostGIS, Elasticsearch, Redis)
- **Messaging** (Kafka + Zookeeper)

## 📂 Structure complète

```
YowYob-Search-Infrastructure/
├── docker/
│   ├── backend/                    # Dockerfiles services backend
│   ├── frontend/                   # Dockerfile Next.js
│   ├── databases/                  # Images DB optimisées
│   ├── docker-compose.yml          # Dev local complet
│   ├── docker-compose.prod.yml     # Prod local
│   └── .env.example
│
├── k8s/                            # Kubernetes manifests
│   ├── namespaces/
│   │   └── yowyob-namespace.yaml
│   ├── configmaps/
│   ├── secrets/
│   ├── deployments/                # Tous les services
│   ├── services/
│   ├── ingress/
│   │   └── yowyob-ingress.yaml     # NGINX Ingress
│   ├── statefulsets/              # PostgreSQL, ES, Redis, Kafka
│   ├── pvcs/
│   ├── hpa/                       # Auto-scaling
│   └── monitoring/
│
├── terraform/                     # Infrastructure as Code
│   ├── main.tf
│   ├── variables.tf
│   ├── environments/
│   └── modules/
│
├── ci-cd/
│   └── .github/workflows/
│       ├── backend-ci.yml
│       ├── frontend-ci.yml
│       ├── deploy-staging.yml
│       └── deploy-prod.yml
│
└── scripts/
    ├── setup-local-env.sh
    ├── deploy.sh
    ├── backup.sh
    └── health-check.sh
```

## 🐳 Docker Compose (dev)

**Lancement complet d'un environnement de développement** :

```bash
# Cloner le repo
git clone https://github.com/your-org/YowYob-Search-Infrastructure.git
cd YowYob-Search-Infrastructure

# Configuration
cp .env.example .env
# Éditer .env avec tes valeurs

# Lancement
docker-compose up --build -d
```

**Inclut automatiquement** :
- **API Gateway** (8080)
- **Search Service** (8082)
- **User Service** (8083)
- **Geo Service** (8084)
- **Crawler Service** (8085)
- **Notification Service** (8086)
- **Frontend Next.js** (3000)
- **PostgreSQL + PostGIS** (5432)
- **Elasticsearch** (9200)
- **Redis** (6379)
- **Kafka + Zookeeper** (9092)
- **Kibana** (5601)
- **Prometheus** (9090)
- **Grafana** (3001)

**Accès rapide** :
```
Frontend:     http://localhost:3000
API Gateway:  http://localhost:8080
Kibana:       http://localhost:5601
Grafana:      http://localhost:3001
Prometheus:   http://localhost:9090
```

## ☸ Kubernetes (prod)

**Namespace principal** : `k8s/namespaces/yowyob.yaml`

**Chaque service contient** :
- `deployment.yaml`
- `service.yaml`
- `ingress.yaml`
- `configmap.yaml`
- `secret.yaml`

**Déploiement rapide** :
```bash
kubectl apply -f k8s/
kubectl apply -f k8s/monitoring/
```

**Ingress NGINX** (yowyob-ingress.yaml) :
```
yowyob.com          → Frontend (3000)
api.yowyob.com      → API Gateway (8080)
```

## 📡 Monitoring

**Stack complète** :
- **Prometheus** : Collecte métriques
- **Grafana** : Dashboards
- **Loki** : Logs centralisés
- **Tempo** : Tracing distribué
- **Alertmanager** : Alertes

**Déploiement** :
```bash
kubectl apply -f k8s/monitoring/
```

**Métriques collectées** :
- Latence API (p50/p95/p99)
- Taux d'erreur (4xx/5xx)
- Throughput (req/s)
- CPU/Mémoire/Disk
- Métriques business (recherches/min)

## 🔐 Gestion des secrets

**Sécurisée via** :
- **Kubernetes Secrets**
- **Sealed Secrets**
- **GitHub Actions Secrets**

**Exemples** :
```
JWT_SECRET
POSTGRES_PASSWORD
ELASTIC_PASSWORD
REDIS_PASSWORD
GRAFANA_PASSWORD
```

## 🚀 CI/CD (GitHub Actions)

**Pipelines automatisés** dans `ci-cd/.github/workflows/` :

1. **backend-ci.yml** : Tests + build Docker backend
2. **frontend-ci.yml** : Tests + build Docker frontend
3. **deploy-staging.yml** : Déploiement staging
4. **deploy-prod.yml** : Déploiement production

**Flux** : Push → Tests → Build → Push Registry → Deploy K8s

## 🧱 Environnements

| Environnement | Technologie       | Usage              |
|---------------|-------------------|--------------------|
| **local**     | Docker Compose    | Développement      |
| **staging**   | Minikube / K3s    | Tests QA           |
| **production**| GKE/AKS/EKS       | Production         |

## 🏗️ Infrastructure as Code (Terraform)

**GKE + CloudSQL** provisionnés via Terraform :

```bash
cd terraform
terraform init
terraform plan -var-file=environments/prod.tfvars
terraform apply -var-file=environments/prod.tfvars
```

## 🔧 Scripts utilitaires

```bash
./scripts/setup-local-env.sh     # Environnement local complet
./scripts/deploy.sh prod         # Déploiement K8s
./scripts/backup.sh              # Sauvegarde DB
./scripts/health-check.sh        # Vérification santé
```

## 📜 Conventions

- **Versioning** : sémantique (semver)
- **Images Docker** : `gcr.io/yowyob/{service}:{tag}`
- **Namespaces K8s** : `yowyob`
- **Ressources** : requests/limits définis
- **Healthchecks** : liveness + readiness

## 🔒 Sécurité

- **HTTPS/TLS** : Let's Encrypt via cert-manager
- **RBAC K8s** : Namespace isolation
- **Network Policies** : Trafic inter-services contrôlé
- **Secrets** : Rotation automatique
- **Scanning** : Trivy + OWASP

***

## 🚀 Quick Start

```bash
# 1. Développement local (5min)
git clone https://github.com/your-org/YowYob-Search-Infrastructure
cd YowYob-Search-Infrastructure
./scripts/setup-local-env.sh

# 2. Accès
# Frontend: http://localhost:3000
# API: http://localhost:8080
# Monitoring: http://localhost:3001
```

**Production** : Suivre Terraform + `kubectl apply -f k8s/`

***

**YowYob Infrastructure** - *Scalable, Resilient, Cloud-Native* 🚀

[![GitHub](https://img.shields.io/badge/GitHub-✓-181717.svghttps://github.com/BrianBrusly/YowYob-Search-Infrastructure  
[![License MIT](https://img.shields.io/badge/License-MIT-yellow.svgLICENSE

[1](https://gist.github.com/ramantehlan/602ad8525699486e097092e4158c5bf1)
[2](https://data.research.cornell.edu/data-management/sharing/writing-readmes-for-research-code-software/)
[3](https://guides.library.queensu.ca/ReadmeTemplate)
[4](https://blogs.incyclesoftware.com/readme-files-for-internal-projects)
[5](https://gitlab.cirad.fr/cirad/template/-/blob/master/README.md)
[6](https://docs.readme.com/main/docs/documentation-structure)
[7](https://www.youtube.com/watch?v=eVGEea7adDM)
[8](https://www.reddit.com/r/opensource/comments/txl9zq/next_level_readme/)
[9](https://data.code.gouv.fr/hosts/gitlab-research.centralesupelec.fr/repositories/myapps-templates%2Fnode/readme)
[10](https://cm.linkedin.com/in/frank-fomekong-441874200)
