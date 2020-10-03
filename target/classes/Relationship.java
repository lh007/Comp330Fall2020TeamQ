package GeneticsApp;
class Relationship{
    Person maleParent;
    Person femaleParent;
    String startDate;
    String endDate;
    String description;
    String id;

    public Relationship(String id) {
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setMaleParent(Person maleParent) {
        this.maleParent = maleParent;
    }

    public void setFemaleParent(Person femaleParent){
        this.femaleParent = femaleParent;
    }

    public void setStartDate(String startDate){
        this.startDate = startDate;
    }

    public void setEndDate(String endDate){
        this.endDate = endDate;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Person getMaleParent() {
        return this.maleParent;
    }

    public Person getFemaleParent() {
        return this.femaleParent;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public String getDescription() {
        return this.description;
    }


}