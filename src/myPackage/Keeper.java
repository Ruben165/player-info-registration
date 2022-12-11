package myPackage;

public class Keeper extends Player implements isHandOkay {
	// Atribut tambahan yang dimiliki seorang pemain "Keeper"
    private int arm_strength;
    
    // Constructor kosong
    public Keeper() {
    	super();
    }

    // Constructor berisi
	public Keeper(String name, String nationality, String club, String position, int age, int jerseyNumber) {
		super(name, nationality, club, position, age, jerseyNumber);
	}
	
	// Metode Abstrak dari super-class (Pemain "Keeper" memiliki kriterianya tersendiri dalam menentukan stamina).
	@Override
	public void genStamina() {
		if(this.getAge()<=20) this.setStamina((int) (Math.random() * (91 - 60)) + 60 + 1); // umur<=20 -> stamina(60 sampai 91 inklusif)
		else if(this.getAge()<=30) this.setStamina((int) (Math.random() * (96 - 63)) + 63 + 1); // umur<=30 -> stamina(63 sampai 96 inklusif)
		else this.setStamina((int) (Math.random() * (91 - 52)) + 52 + 1); // umur yang lebih tua -> stamina(52 sampai 91 inklusif)
	}
	
	// Metode Abstrak dari interface (Pemain "Keeper" merupakan satu-satunya posisi yang diperbolehkan menggunakan tangan di dalam kotak penalti untuk menjaga gawang).
	@Override
	public void genArmStrength() {
		if(this.getAge()<=20) this.setArm_strength((int) (Math.random() * (91 - 45)) + 45 + 1); // umur<=20 -> arm_strength(45 sampai 91 inklusif)
		else if(this.getAge()<=30) this.setArm_strength((int) (Math.random() * (100 - 45)) + 45 + 1); // umur<=30 -> arm_strength(45 sampai 100 inklusif)
		else this.setArm_strength((int) (Math.random() * (91 - 39)) + 39 + 1); // umur yang lebih tua -> arm_strength(39 sampai 91 inklusif)
		// Secara keseluruhan, max arm_strength = 100 dan min 39
	}

	// Setter AND Getter
	public int getArm_strength() {
		return arm_strength;
	}

	public void setArm_strength(int arm_strength) {
		this.arm_strength = arm_strength;
	}
}
