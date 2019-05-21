package com.company.database.queries;

public class CreateRecord {
    private int user_id;
    private String username;
    private int create;

    public CreateRecord() {
    }

    public CreateRecord(int user_id, String username, int create) {
        this.user_id = user_id;
        this.username = username;
        this.create = create;
    }

    @Override
    public String toString() {
        return "CreateRecord{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", create=" + create +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCreate() {
        return create;
    }

    public void setCreate(int create) {
        this.create = create;
    }
}
