package GeneticsApp;

class Person{
    String firstName;
    String lastName;
    String dob;
    String dod;
    String birthPlace;
    String deathPlace;
    String suffix;
    String parents;
    String id;

    public Person()
    {
        //TODO
    }
    public void setId(String id)
    {
        this.id = id;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public void setDob(String dob)
    {
        this.dob = dob;
    }
    public void setDod(String dod)
    {
        this.dod = dod;
    }
    public void setBirthPlace(String birthPlace)
    {
        this.birthPlace = birthPlace;
    }
    public void setDeathPlace(String deathPlace)
    {
        this.deathPlace = deathPlace;
    }
    public void setSuffix(String suffix){this.suffix = suffix;}
    public void setParents(String parents){this.parents = parents;}
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getDob(){
        return this.dob;
    }
    public String getDod(){
        return this.dod;
    }
    public String getBirthPlace(){
        return this.birthPlace;
    }
    public String getDeathPlace(){
        return this.deathPlace;
    }
    public String getSuffix(){return this.suffix;}
    public String getId(){return this.id;}
    public String getParents(){return this.parents;}

}
