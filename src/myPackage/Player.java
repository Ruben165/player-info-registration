package myPackage;

public abstract class Player {
	// Atribut-atribut yang dimiliki seorang pemain.
    private String name, nationality, club, position;
    private int age, jerseyNumber, stamina;
   
    // Metode 'Abstrak' yang harus dimiliki semua sub-class dari kelas ini. 
    public abstract void genStamina();
   
    // Constructor kosong
    public Player() {}
   
    // Constructor berisi
    public Player(String name, String nationality, String club, String position, int age, int jerseyNumber) {
	    this.name = name;
	    this.nationality = nationality;
	    this.club = club;
	    this.position = position;
	    this.age = age;
	    this.jerseyNumber = jerseyNumber;
    }

    // Setter AND Getter
    public String getName() {
	    return name;
    }

    public String getNationality() {
	    return nationality;
    }

    public String getClub() {
	    return club;
    }

    public void setClub(String club) {
	    this.club = club;
    }

    public String getPosition() {
	    return position;
    }

    public int getAge() {
	    return age;
    }

    public void setAge(int age) {
	    this.age = age;
    }

    public int getJerseyNumber() {
	    return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
	    this.jerseyNumber = jerseyNumber;
    }

    public int getStamina() {
	    return stamina;
    }

    public void setStamina(int stamina) {
	    this.stamina = stamina;
    }
}
