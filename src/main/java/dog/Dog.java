package dog;


import java.time.LocalDate;


public class Dog {
    private int dogId;
    private LocalDate bDate;
    private String breed;
    private String gender;
    private String dname;
    private String uId;

    public Dog() { }


	public Dog(LocalDate bDate, String breed, String gender, String dname, String uId) {
		this.bDate = bDate;
		this.breed = breed;
		this.gender = gender;
		this.dname = dname;
		this.uId = uId;
	}


	public Dog(int dogId, String breed, String dname) {
		this.dogId = dogId;
		this.breed = breed;
		this.dname = dname;
	}
	
	
	
	public Dog(String breed, String dname, String uId) {
		this.breed = breed;
		this.dname = dname;
		this.uId = uId;
	}


	public Dog(int dogId, LocalDate bDate, String breed, String gender, String dname) {
		this.dogId = dogId;
		this.bDate = bDate;
		this.breed = breed;
		this.gender = gender;
		this.dname = dname;
	}


	public Dog(int dogId, LocalDate bDate, String breed, String gender, String dname, String uId) {
        this.dogId = dogId;
        this.bDate = bDate;
        this.breed = breed;
        this.gender = gender;
        this.dname = dname;
        this.uId = uId;
    }

   

	@Override
    public String toString() {
    	return "Dog{" +
    			"dogId=" + dogId +
    			", bDate=" + bDate +
    			", breed='" + breed + '\'' +
    			", gender='" + gender + '\'' +
    			", dname='" + dname + '\'' +
    			", uId=" + uId +
    			'}';
    }
    
    public void setDogId(int dogId) {
        this.dogId = dogId;
    }

    public void setbDate(LocalDate bDate) {
        this.bDate = bDate;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public void setUserId(String uId) {
        this.uId = uId;
    }

    public int getDogId() {
        return dogId;
    }

    public LocalDate getbDate() {
        return bDate;
    }

    public String getBreed() {
        return breed;
    }

    public String getGender() {
        return gender;
    }

    public String getDname() {
        return dname;
    }

    public String getUId() {
        return uId;
    }
}
