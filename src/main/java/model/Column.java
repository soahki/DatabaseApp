package model;

public class Column {
    private String name;
    private String type;
    private String accessRights;
    private boolean isPrimaryKey;
    private boolean isForeignKey;
    private String foreignKeyReference;

    public Column(String name, String type) {
        this(name, type, null, false, false, null);
    }

    public Column(String name, String type, String accessRights, boolean isPrimaryKey, boolean isForeignKey, String foreignKeyReference) {
        this.name = name;
        this.type = type;
        this.accessRights = accessRights;
        this.isPrimaryKey = isPrimaryKey;
        this.isForeignKey = isForeignKey;
        this.foreignKeyReference = foreignKeyReference;
    }
}
