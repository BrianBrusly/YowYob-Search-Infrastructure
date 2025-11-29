# YowYob Search - Infrastructure

**Infrastructure as Code complète** pour la plateforme YowYob Search PWA - Orchestration Docker, Kubernetes, Monitoring et Déploiement Automatisé

## Architecture Globale

Ce repository contient toute l'infrastructure nécessaire pour exécuter la plateforme YowYob Search en environnement de développement local et en production cloud.

### Écosystème Complet

```
┌─────────────────────────────────────────────────────────────────┐
│                      YOWYOB SEARCH PLATFORM                     │
├─────────────────────────────────────────────────────────────────┤
│                                                                 │
│  ┌─────────────────┐    ┌─────────────────────────────────────┐ │
│  │    FRONTEND     │    │           BACKEND API               │ │
│  │   Next.js 14    │◄──►│    Spring Boot Microservices        │ │
│  │     PWA         │    │                                     │ │
│  └─────────────────┘    └─────────────────────────────────────┘ │
│               │                              │                  │
│               │                              │                  │
│               ▼                              ▼                  │
│  ┌─────────────────────────┐  ┌─────────────────────────────┐   │
│  │    NGINX INGRESS        │  │     SERVICE MESH            │   │
│  │  - TLS Termination      │  │  - Load Balancing           │   │
│  │  - Rate Limiting        │  │  - Circuit Breaker          │   │
│  │  - CORS                 │  │  - Retry Policies           │   │
│  └─────────────────────────┘  └─────────────────────────────┘   │
│                                                                 │
│  ┌──────────────────────────────────────────────────────────────┤
│  │                    DATA LAYER                              │ │
│  │  ┌──────────┐ ┌────────────┐ ┌──────────┐ ┌────────────┐   │ │
│  │  │PostgreSQL│ │Elasticsearch││  Redis   │ │   Kafka    │   │ │
│  │  │ +PostGIS │ │  Cluster   │ │  Cache   │ │   Bus      │   │ │
│  │  └──────────┘ └────────────┘ └──────────┘ └────────────┘   │ │
│  └────────────────────────────────────────────────────────────┘ │
│                                                                 │
│  ┌──────────────────────────────────────────────────────────────┤
│  │                 OBSERVABILITY STACK                        │ │
│  │  ┌──────────┐ ┌──────────┐ ┌──────────┐ ┌────────────┐     │ │
│  │  │Prometheus│ │ Grafana  │ │   Loki   │ │   Tempo    │     │ │
│  │  │ Metrics  │ │Dashboard │ │  Logs    │ │  Tracing   │     │ │
│  │  └──────────┘ └──────────┘ └──────────┘ └────────────┘     │ │
│  └────────────────────────────────────────────────────────────┘ │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
```

## Structure du Repository

```
YowYob-Search-Infrastructure/
│
├── 📁 docker/                          # Environnements Docker
│   ├── 📁 backend/                     # Dockerfiles des services backend
│   │   ├── api-gateway.Dockerfile
│   │   ├── search-service.Dockerfile
│   │   ├── user-service.Dockerfile
│   │   ├── geo-service.Dockerfile
│   │   ├── crawler-service.Dockerfile
│   │   ├── notification-service.Dockerfile
│   │   ├── shop-service.Dockerfile
│   │   └── stats-service.Dockerfile
│   │
│   ├── 📁 frontend/                    # Dockerfile Next.js
│   │   └── frontend.Dockerfile
│   │
│   ├── 📁 databases/                   # Images bases de données optimisées
│   │   ├── postgres/
│   │   │   └── Dockerfile
│   │   ├── elasticsearch/
│   │   │   └── Dockerfile
│   │   ├── redis/
│   │   │   └── Dockerfile
│   │   └── kafka/
│   │       └── Dockerfile
│   │
│   ├── 📁 monitoring/                  # Stack monitoring
│   │   ├── prometheus/
│   │   ├── grafana/
│   │   ├── loki/
│   │   └── tempo/
│   │
│   ├── docker-compose.dev.yml          # Développement local complet
│   ├── docker-compose.prod.yml         # Production locale
│   ├── docker-compose.monitoring.yml   # Stack monitoring seule
│   └── .env.example                    # Variables d'environnement
│
├── 📁 k8s/                             # Manifests Kubernetes
│   ├── 📁 namespaces/
│   │   ├── yowyob-namespace.yaml
│   │   └── yowyob-monitoring.yaml
│   │
│   ├── 📁 gateway/                     # API Gateway
│   │   ├── deployment.yaml
│   │   ├── service.yaml
│   │   ├── configmap.yaml
│   │   └── hpa.yaml
│   │
│   ├── 📁 search-service/              # Service recherche
│   │   ├── deployment.yaml
│   │   ├── service.yaml
│   │   ├── configmap.yaml
│   │   └── hpa.yaml
│   │
│   ├── 📁 user-service/                # Service utilisateur
│   │   ├── deployment.yaml
│   │   ├── service.yaml
│   │   └── secret.yaml
│   │
│   ├── 📁 geo-service/                 # Service géolocalisation
│   │   ├── deployment.yaml
│   │   ├── service.yaml
│   │   └── configmap.yaml
│   │
│   ├── 📁 crawler-service/             # Service crawling
│   │   ├── deployment.yaml
│   │   ├── service.yaml
│   │   └── cronjob.yaml
│   │
│   ├── 📁 notification-service/        # Service notifications
│   │   ├── deployment.yaml
│   │   ├── service.yaml
│   │   └── secret.yaml
│   │
│   ├── 📁 shop-service/                # Service e-commerce
│   │   ├── deployment.yaml
│   │   ├── service.yaml
│   │   └── configmap.yaml
│   │
│   ├── 📁 stats-service/               # Service analytics
│   │   ├── deployment.yaml
│   │   ├── service.yaml
│   │   └── configmap.yaml
│   │
│   ├── 📁 frontend/                    # Application Next.js
│   │   ├── deployment.yaml
│   │   ├── service.yaml
│   │   ├── configmap.yaml
│   │   └── hpa.yaml
│   │
│   ├── 📁 ingress/                     # Configuration ingress
│   │   ├── ingress-nginx.yaml
│   │   ├── tls-secret.yaml
│   │   └── cert-manager.yaml
│   │
│   ├── 📁 databases/                   # Bases de données stateful
│   │   ├── 📁 postgres/
│   │   │   ├── statefulset.yaml
│   │   │   ├── service.yaml
│   │   │   ├── pvc.yaml
│   │   │   └── secret.yaml
│   │   │
│   │   ├── 📁 elasticsearch/
│   │   │   ├── statefulset.yaml
│   │   │   ├── service.yaml
│   │   │   ├── pvc.yaml
│   │   │   └── configmap.yaml
│   │   │
│   │   ├── 📁 redis/
│   │   │   ├── statefulset.yaml
│   │   │   ├── service.yaml
│   │   │   └── secret.yaml
│   │   │
│   │   └── 📁 kafka/
│   │       ├── statefulset.yaml
│   │       ├── service.yaml
│   │       ├── zookeeper.yaml
│   │       └── configmap.yaml
│   │
│   ├── 📁 monitoring/                  # Stack observabilité
│   │   ├── 📁 prometheus/
│   │   │   ├── deployment.yaml
│   │   │   ├── service.yaml
│   │   │   ├── configmap.yaml
│   │   │   └── serviceaccount.yaml
│   │   │
│   │   ├── 📁 grafana/
│   │   │   ├── deployment.yaml
│   │   │   ├── service.yaml
│   │   │   ├── configmap.yaml
│   │   │   └── datasources.yaml
│   │   │
│   │   ├── 📁 loki/
│   │   │   ├── deployment.yaml
│   │   │   ├── service.yaml
│   │   │   └── configmap.yaml
│   │   │
│   │   ├── 📁 tempo/
│   │   │   ├── deployment.yaml
│   │   │   ├── service.yaml
│   │   │   └── configmap.yaml
│   │   │
│   │   └── 📁 alertmanager/
│   │       ├── deployment.yaml
│   │       ├── service.yaml
│   │       └── configmap.yaml
│   │
│   └── 📁 networking/                  # Configuration réseau
│       ├── network-policies.yaml
│       ├── service-mesh.yaml
│       └── dns-config.yaml
│
├── 📁 terraform/                       # Infrastructure as Code
│   ├── main.tf
│   ├── variables.tf
│   ├── outputs.tf
│   ├── 📁 modules/
│   │   ├── kubernetes/
│   │   ├── database/
│   │   ├── networking/
│   │   └── monitoring/
│   │
│   └── 📁 environments/
│       ├── dev/
│       ├── staging/
│       └── prod/
│
├── 📁 ci-cd/                           # Pipelines d'intégration
│   └── 📁 .github/workflows/
│       ├── backend-ci.yml
│       ├── frontend-ci.yml
│       ├── security-scan.yml
│       ├── deploy-staging.yml
│       └── deploy-production.yml
│
├── 📁 scripts/                         # Scripts utilitaires
│   ├── setup-local-env.sh
│   ├── deploy-k8s.sh
│   ├── backup-databases.sh
│   ├── health-check.sh
│   ├── init-elasticsearch.sh
│   ├── init-postgres.sh
│   ├── generate-secrets.sh
│   └── monitoring-setup.sh
│
├── 📁 docs/                            # Documentation
│   ├── architecture.md
│   ├── deployment-guide.md
│   ├── monitoring-guide.md
│   ├── troubleshooting.md
│   └── security.md
│
├── 📁 config/                          # Fichiers de configuration
│   ├── nginx/
│   ├── prometheus/
│   ├── grafana/
│   └── loki/
│
└── README.md
```

## Flux

                          ┌──────────────────────────┐
                          │     YOWYOB FRONTEND      │
                          │ Next.js (SSR + PWA + SEO)│
                          └───────────────┬──────────┘
                                          │
                              HTTPS / NGINX INGRESS
                                          │
                    ┌─────────────────────▼─────────────────────┐
                    │              API GATEWAY                  │
                    │      Spring Cloud Gateway (8080)          │
                    └───────────────┬───────────────────────────┘
                                    │
           ┌────────────────────────────┼────────────────────────────────────────┐
           │                            │                                        │
    ┌──────▼──────┐           ┌─────────▼────────┐                    ┌─────────▼────────┐
    │ SEARCH SVC  │           │ USER SVC          │                   │ GEO SVC          │
    │Elasticsearch│           │ PostgreSQL (Users)│                   │ PostGIS / OSM    │
    └──────┬──────┘           └─────────┬────────┘                    └─────────┬────────┘
           │                            │                                        │
           ▼                            ▼                                        ▼
    ┌──────────────┐            ┌──────────────┐                         ┌──────────────┐
    │ CRAWLER SVC  │──► Kafka ► │ STATS SVC    │ ◄──────────────────────►│ NOTIF SVC    │
    │ JSoup / Tika │            │ Analytics    │                         │ Web Push     │
    └──────────────┘            └──────────────┘                         └──────────────┘

                  ┌────────────────────────────────────────────┐
                  │            INFRASTRUCTURE DATA             │
                  │ Elasticsearch • Redis • PostGIS • Kafka    │
                  └────────────────────────────────────────────┘

               ┌────────────────────────────────────────────────────┐
               │                 OBSERVABILITY                      │
               │ Grafana • Prometheus • Loki • Tempo • Kibana       │
               └────────────────────────────────────────────────────┘


## Environnements Supportés

### Développement Local (Docker Compose)

**Fichier :** `docker/docker-compose.dev.yml`

Services inclus :
- Frontend Next.js (port 3000)
- API Gateway (port 8080)
- PostgreSQL + PostGIS (port 5432)
- Elasticsearch (port 9200)
- Redis (port 6379)
- Kafka + Zookeeper (port 9092)
- Kibana (port 5601)
- Prometheus (port 9090)
- Grafana (port 3001)

**Lancement :**
```bash
git clone https://github.com/BrianBrusly/YowYob-Search-Infrastructure.git
cd YowYob-Search-Infrastructure

cp .env.example .env
# Configurer les variables d'environnement

docker-compose -f docker/docker-compose.dev.yml up -d
```

**Accès rapide :**
- Frontend : http://localhost:3000
- API : http://localhost:8080
- Elasticsearch : http://localhost:9200
- Kibana : http://localhost:5601
- Grafana : http://localhost:3001

### Production Kubernetes

**Configuration des namespaces :**
```yaml
# k8s/namespaces/yowyob-namespace.yaml
apiVersion: v1
kind: Namespace
metadata:
  name: yowyob
  labels:
    name: yowyob
    environment: production
```

**Déploiement complet :**
```bash
# Appliquer tous les manifests
kubectl apply -f k8s/namespaces/
kubectl apply -f k8s/databases/
kubectl apply -f k8s/gateway/
kubectl apply -f k8s/search-service/
kubectl apply -f k8s/user-service/
kubectl apply -f k8s/geo-service/
kubectl apply -f k8s/crawler-service/
kubectl apply -f k8s/notification-service/
kubectl apply -f k8s/shop-service/
kubectl apply -f k8s/stats-service/
kubectl apply -f k8s/frontend/
kubectl apply -f k8s/ingress/
kubectl apply -f k8s/monitoring/
```

## Configuration des Services

### API Gateway (Spring Cloud Gateway)

**Port :** 8080

**Configuration des routes :**
```yaml
# k8s/gateway/configmap.yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: gateway-config
  namespace: yowyob
data:
  application.yml: |
    spring:
      cloud:
        gateway:
          routes:
            - id: search-service
              uri: lb://search-service
              predicates:
                - Path=/api/search/**
              filters:
                - StripPrefix=1
                - name: CircuitBreaker
                  args:
                    name: searchCircuitBreaker
                    fallbackUri: forward:/fallback/search
                    
            - id: user-service
              uri: lb://user-service
              predicates:
                - Path=/api/auth/**, /api/users/**
              filters:
                - StripPrefix=1
```

### Service de Recherche (Elasticsearch)

**Configuration :**
```yaml
# k8s/search-service/configmap.yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: search-service-config
  namespace: yowyob
data:
  application.yml: |
    spring:
      data:
        elasticsearch:
          cluster-nodes: elasticsearch:9200
          connection-timeout: 10s
          socket-timeout: 30s
    elasticsearch:
      indices:
        documents: yowyob-documents
        products: yowyob-products
```

### Service Géolocalisation (PostGIS + OSM)

**Configuration :**
```yaml
# k8s/geo-service/configmap.yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: geo-service-config
  namespace: yowyob
data:
  application.yml: |
    geo:
      nominatim:
        base-url: https://nominatim.openstreetmap.org
        rate-limit: 1000
      cache:
        ttl: 86400 # 24 heures
```

## Stack de Monitoring et Observabilité

### Métriques Collectées

**Prometheus scrape configuration :**
```yaml
# k8s/monitoring/prometheus/configmap.yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: prometheus-config
  namespace: yowyob-monitoring
data:
  prometheus.yml: |
    global:
      scrape_interval: 15s
      evaluation_interval: 15s
    
    scrape_configs:
      - job_name: 'yowyob-services'
        kubernetes_sd_configs:
          - role: endpoints
        relabel_configs:
          - source_labels: [__meta_kubernetes_service_annotation_prometheus_io_scrape]
            action: keep
            regex: true
            
      - job_name: 'elasticsearch'
        static_configs:
          - targets: ['elasticsearch:9200']
            
      - job_name: 'redis'
        static_configs:
          - targets: ['redis:6379']
```

### Dashboards Grafana

**Dashboards inclus :**
- **API Performance** : Latence, taux d'erreur, throughput
- **Search Analytics** : Requêtes par minute, temps de réponse, cache hit rate
- **System Metrics** : CPU, mémoire, disk I/O
- **Business Metrics** : Utilisateurs actifs, recherches populaires
- **Database Performance** : Requêtes PostgreSQL, performance Elasticsearch

### Logs Centralisés (Loki)

**Configuration :**
```yaml
# k8s/monitoring/loki/configmap.yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: loki-config
  namespace: yowyob-monitoring
data:
  loki.yaml: |
    auth_enabled: false
    server:
      http_listen_port: 3100
    common:
      path_prefix: /tmp/loki
      storage:
        filesystem:
          chunks_directory: /tmp/loki/chunks
          rules_directory: /tmp/loki/rules
      replication_factor: 1
      ring:
        instance_addr: 127.0.0.1
        kvstore:
          store: inmemory
    schema_config:
      configs:
        - from: 2020-10-24
          store: boltdb-shipper
          object_store: filesystem
          schema: v11
          index:
            prefix: index_
            period: 24h
```

## Sécurité

### Gestion des Secrets

**Secrets Kubernetes :**
```bash
# Génération des secrets
./scripts/generate-secrets.sh

# Secrets créés :
# - jwt-secret (clés RSA pour JWT)
# - postgres-secret (mot de passe PostgreSQL)
# - redis-secret (mot de passe Redis)
# - smtp-secret (credentials SMTP)
# - vapid-secret (clés VAPID pour notifications push)
```

**Exemple de secret :**
```yaml
# k8s/user-service/secret.yaml
apiVersion: v1
kind: Secret
metadata:
  name: user-service-secrets
  namespace: yowyob
type: Opaque
data:
  jwt-private-key: LS0tLS1CRUdJTiBQUk...
  jwt-public-key: LS0tLS1CRUdJTiBQVk...
  bcrypt-rounds: MTI=
```

### Network Policies

**Isolation réseau :**
```yaml
# k8s/networking/network-policies.yaml
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: backend-isolation
  namespace: yowyob
spec:
  podSelector: {}
  policyTypes:
  - Ingress
  - Egress
  ingress:
  - from:
    - podSelector:
        matchLabels:
          app: api-gateway
    ports:
    - protocol: TCP
      port: 8080
```

## CI/CD Pipeline

### GitHub Actions Workflows

**Backend CI :**
```yaml
# ci-cd/.github/workflows/backend-ci.yml
name: Backend CI
on:
  push:
    branches: [ main, develop ]
    paths:
      - 'backend/**'
      - 'docker/backend/**'

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 21
        uses: actions/setup-java@v3
        with:
          java-version: '21'
          distribution: 'temurin'
      
      - name: Build and Test
        run: |
          cd ../YowYob-Search-Backend
          mvn clean verify
```

**Déploiement Production :**
```yaml
# ci-cd/.github/workflows/deploy-production.yml
name: Deploy to Production
on:
  push:
    tags:
      - 'v*'

jobs:
  deploy:
    runs-on: ubuntu-latest
    environment: production
    steps:
      - uses: actions/checkout@v3
      
      - name: Configure K8s
        run: |
          echo "${{ secrets.KUBECONFIG }}" > kubeconfig
          export KUBECONFIG=kubeconfig
          
      - name: Deploy to Kubernetes
        run: ./scripts/deploy-k8s.sh production
```

## Scripts Utilitaires

### Initialisation de l'Environnement

**setup-local-env.sh :**
```bash
#!/bin/bash
set -e

echo "🚀 Configuration de l'environnement YowYob local..."

# Vérification des prérequis
command -v docker >/dev/null 2>&1 || { echo "Docker requis"; exit 1; }
command -v docker-compose >/dev/null 2>&1 || { echo "Docker Compose requis"; exit 1; }

# Création des dossiers de données
mkdir -p data/elasticsearch data/postgres data/redis

# Copie de l'environnement
cp .env.example .env

echo "📦 Construction des images..."
docker-compose -f docker/docker-compose.dev.yml build

echo "🎯 Démarrage des services..."
docker-compose -f docker/docker-compose.dev.yml up -d

echo "⏳ Initialisation des bases de données..."
sleep 30

./scripts/init-postgres.sh
./scripts/init-elasticsearch.sh

echo "✅ Environnement prêt!"
echo "🌐 Frontend: http://localhost:3000"
echo "🔗 API: http://localhost:8080"
echo "📊 Monitoring: http://localhost:3001"
```

### Déploiement Kubernetes

**deploy-k8s.sh :**
```bash
#!/bin/bash
set -e

ENVIRONMENT=${1:-staging}

echo "🚀 Déploiement sur l'environnement $ENVIRONMENT"

# Validation de l'environnement
if [[ ! "$ENVIRONMENT" =~ ^(staging|production)$ ]]; then
    echo "❌ Environnement invalide: $ENVIRONMENT"
    exit 1
fi

# Application des manifests de base
kubectl apply -f k8s/namespaces/

# Déploiement des bases de données
kubectl apply -f k8s/databases/

# Attente que les bases soient ready
kubectl wait --for=condition=ready pod -l app=postgres -n yowyob --timeout=300s
kubectl wait --for=condition=ready pod -l app=elasticsearch -n yowyob --timeout=300s

# Initialisation des bases
kubectl apply -f k8s/jobs/init-db.yaml
kubectl wait --for=condition=complete job/init-db -n yowyob --timeout=300s

# Déploiement des services
kubectl apply -f k8s/gateway/
kubectl apply -f k8s/search-service/
kubectl apply -f k8s/user-service/
kubectl apply -f k8s/geo-service/
kubectl apply -f k8s/crawler-service/
kubectl apply -f k8s/notification-service/
kubectl apply -f k8s/shop-service/
kubectl apply -f k8s/stats-service/
kubectl apply -f k8s/frontend/

# Déploiement de l'ingress
kubectl apply -f k8s/ingress/

echo "✅ Déploiement terminé pour l'environnement $ENVIRONMENT"
```

## Stratégies de Déploiement

### Blue-Green Deployment

**Configuration :**
```yaml
# k8s/frontend/deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: frontend-blue
  namespace: yowyob
  labels:
    app: frontend
    version: blue
spec:
  replicas: 3
  selector:
    matchLabels:
      app: frontend
      version: blue
  template:
    metadata:
      labels:
        app: frontend
        version: blue
    spec:
      containers:
      - name: frontend
        image: registry.yowyob.com/frontend:v1.2.3
        ports:
        - containerPort: 3000
```

### Auto-scaling Horizontal

**Configuration HPA :**
```yaml
# k8s/search-service/hpa.yaml
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  name: search-service-hpa
  namespace: yowyob
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: search-service
  minReplicas: 2
  maxReplicas: 10
  metrics:
  - type: Resource
    resource:
      name: cpu
      target:
        type: Utilization
        averageUtilization: 70
  - type: Pods
    pods:
      metric:
        name: http_requests_per_second
      target:
        type: AverageValue
        averageValue: "100"
```

## Résolution des Problèmes Courants

### Vérification de la Santé

**health-check.sh :**
```bash
#!/bin/bash

echo "🔍 Vérification de la santé des services..."

# Vérification des pods
kubectl get pods -n yowyob

# Vérification des services
kubectl get services -n yowyob

# Vérification des logs
kubectl logs -l app=api-gateway -n yowyob --tail=50

# Test des endpoints
API_URL="http://localhost:8080"
curl -f "$API_URL/actuator/health" || echo "❌ API Gateway inaccessible"
curl -f "$API_URL/api/search/suggestions?q=test" || echo "❌ Service search inaccessible"

echo "✅ Vérification terminée"
```

### Sauvegarde des Bases de Données

**backup-databases.sh :**
```bash
#!/bin/bash
set -e

BACKUP_DIR="./backups/$(date +%Y%m%d_%H%M%S)"
mkdir -p "$BACKUP_DIR"

echo "💾 Sauvegarde des bases de données..."

# Sauvegarde PostgreSQL
kubectl exec -n yowyob deployment/postgres -- pg_dump -U yowyob yowyob_db > "$BACKUP_DIR/postgres.sql"

# Sauvegarde Elasticsearch
kubectl exec -n yowyob deployment/elasticsearch -- elasticdump \
  --input=http://localhost:9200/yowyob-documents \
  --output="$BACKUP_DIR/elasticsearch-documents.json" \
  --type=data

echo "✅ Sauvegarde terminée dans: $BACKUP_DIR"
```

## Support et Maintenance

### Monitoring des Performances

**Métriques clés à surveiller :**
- Temps de réponse API (p95 < 200ms)
- Taux d'erreur HTTP (< 1%)
- Utilisation CPU/Mémoire (< 80%)
- Latence Elasticsearch (< 100ms)
- Taux de cache Redis (> 80%)

### Mises à Jour de Sécurité

**Processus de mise à jour :**
1. Scan de vulnérabilités avec Trivy
2. Mise à jour des images Docker
3. Tests de régression
4. Déploiement en staging
5. Déploiement en production avec blue-green

## Licence

MIT License - Voir le fichier [LICENSE](LICENSE) pour plus de détails.

## Support

- **Issues GitHub** : [https://github.com/BrianBrusly/YowYob-Search-Infrastructure/issues](https://github.com/BrianBrusly/YowYob-Search-Infrastructure/issues)
- **Documentation** : [docs/](docs/)
- **Email** : infrastructure@yowyob.com

---

**Infrastructure YowYob** - *Scalable, Resilient, Cloud-Native*

