/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.validation.constraints.NotBlank
 */
package com.example.template.DTO.Favorite;

import jakarta.validation.constraints.NotBlank;

public class FavoriteRequest {
    @NotBlank(message="Item ID is required")
    private @NotBlank(message="Item ID is required") String itemId;
    @NotBlank(message="Item type is required")
    private @NotBlank(message="Item type is required") String itemType;
    @NotBlank(message="Title is required")
    private @NotBlank(message="Title is required") String title;
    private String url;
    private String notes;

    public String getItemId() {
        return this.itemId;
    }

    public String getItemType() {
        return this.itemType;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FavoriteRequest)) {
            return false;
        }
        FavoriteRequest other = (FavoriteRequest)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$itemId = this.getItemId();
        String other$itemId = other.getItemId();
        if (this$itemId == null ? other$itemId != null : !this$itemId.equals(other$itemId)) {
            return false;
        }
        String this$itemType = this.getItemType();
        String other$itemType = other.getItemType();
        if (this$itemType == null ? other$itemType != null : !this$itemType.equals(other$itemType)) {
            return false;
        }
        String this$title = this.getTitle();
        String other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) {
            return false;
        }
        String this$url = this.getUrl();
        String other$url = other.getUrl();
        if (this$url == null ? other$url != null : !this$url.equals(other$url)) {
            return false;
        }
        String this$notes = this.getNotes();
        String other$notes = other.getNotes();
        return !(this$notes == null ? other$notes != null : !this$notes.equals(other$notes));
    }

    protected boolean canEqual(Object other) {
        return other instanceof FavoriteRequest;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $itemId = this.getItemId();
        result = result * 59 + ($itemId == null ? 43 : $itemId.hashCode());
        String $itemType = this.getItemType();
        result = result * 59 + ($itemType == null ? 43 : $itemType.hashCode());
        String $title = this.getTitle();
        result = result * 59 + ($title == null ? 43 : $title.hashCode());
        String $url = this.getUrl();
        result = result * 59 + ($url == null ? 43 : $url.hashCode());
        String $notes = this.getNotes();
        result = result * 59 + ($notes == null ? 43 : $notes.hashCode());
        return result;
    }

    public String toString() {
        return "FavoriteRequest(itemId=" + this.getItemId() + ", itemType=" + this.getItemType() + ", title=" + this.getTitle() + ", url=" + this.getUrl() + ", notes=" + this.getNotes() + ")";
    }

    public FavoriteRequest() {
    }

    public FavoriteRequest(String itemId, String itemType, String title, String url, String notes) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.title = title;
        this.url = url;
        this.notes = notes;
    }
}
