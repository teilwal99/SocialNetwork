package com.example.demo.DTO;

public class PostResponseDTO {
    private String id;
    private String imageUrl;
    private String caption;

    private int comments;
    private long timestamp;
    private boolean isLiked;
    private int likes;

    private AuthorDTO author;

    // Full constructor
    public PostResponseDTO(String id, String imageUrl, String caption, int comments,
                           long timestamp, boolean isLiked, int likes, AuthorDTO author) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.caption = caption;
        this.comments = comments;
        this.timestamp = timestamp;
        this.isLiked = isLiked;
        this.likes = likes;
        this.author = author;
    }

    // Empty constructor (required for serialization)
    public PostResponseDTO() {}

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getComments() {
        return comments;
    }
    public void setComments(int comments) {
        this.comments = comments;
    }

    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isLiked() {
        return isLiked;
    }
    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }

    public AuthorDTO getAuthor() {
        return author;
    }
    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    // Nested Author DTO class
    public static class AuthorDTO {
        private String _id;
        private String username;
        private String image;

        public AuthorDTO() {}

        public AuthorDTO(String _id, String username, String image) {
            this._id = _id;
            this.username = username;
            this.image = image;
        }

        public String get_id() {
            return _id;
        }
        public void set_id(String _id) {
            this._id = _id;
        }

        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }

        public String getImage() {
            return image;
        }
        public void setImage(String image) {
            this.image = image;
        }
    }
}
