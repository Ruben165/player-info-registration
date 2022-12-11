package myPackage;

public class Back extends Player {
	// Atribut tambahan yang dimiliki seorang pemain "Back".
    private int defend_capability;
    
    // Constructor kosong
    public Back() {
    	super();
    }

    // Constructor berisi
	public Back(String name, String nationality, String club, String position, int age, int jerseyNumber, int defend_capability) {
		super(name, nationality, club, position, age, jerseyNumber);
		this.defend_capability = defend_capability;
	}
	
	// Metode Abstrak dari super-class (Pemain "Back" memiliki kriterianya tersendiri dalam menentukan stamina).
	@Override
	public void genStamina() {
		if(this.getAge()<=20) this.setStamina((int) (Math.random() * (96 - 65)) + 65 + 1); // umur<=20 -> stamina(65 sampai 96 inklusif)
		else if(this.getAge()<=30) this.setStamina((int) (Math.random() * (101 - 65)) + 65 + 1); // umur<=30 -> stamina(65 sampai 101 inklusif)
		else this.setStamina((int) (Math.random() * (91 - 55)) + 55 + 1); // umur yang lebih tua -> stamina(55 sampai 91 inklusif)
	}
    
	// Setter AND Getter
	public int getDefend_capability() {
		return defend_capability;
	}
	
	public void setDefend_capability(int defend_capability) {
		this.defend_capability = defend_capability;
	}
}
