package model;

public class Column {
    private String name;
    private String type;
    private boolean isPrimaryKey;
    private boolean isForeignKey;
    private String foreignKeyReference;

    public Column(String name, String type) {
        this(name, type, false, false, null);
    }

    public Column(String name, String type, boolean isPrimaryKey, boolean isForeignKey, String foreignKeyReference) {
        this.name = name;
        this.type = type;
        this.isPrimaryKey = isPrimaryKey;
        this.isForeignKey = isForeignKey;
        this.foreignKeyReference = foreignKeyReference;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public boolean isForeignKey() {
        return isForeignKey;
    }

    public String getForeignKeyReference() {
        return foreignKeyReference;
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", isPrimaryKey=" + isPrimaryKey +
                ", isForeignKey=" + isForeignKey +
                ", foreignKeyReference='" + foreignKeyReference + '\'' +
                '}';
    }
}
