package model;

public class Table {
    private String name;
    private String type;
    private String remarks;
    private String identifier;
    private String identifierGeneration;

    public Table(String name, String type) {
        this(name, type, null, null, null);
    }

    public Table(String name, String type, String remarks, String identifier, String identifierGeneration) {
        this.name = name;
        this.type = type;
        this.remarks = remarks;
        this.identifier = identifier;
        this.identifierGeneration = identifierGeneration;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getIdentifierGeneration() {
        return identifierGeneration;
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", remarks='" + remarks + '\'' +
                ", identifier='" + identifier + '\'' +
                ", identifierGeneration='" + identifierGeneration + '\'' +
                '}';
    }
}
