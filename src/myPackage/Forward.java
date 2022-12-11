package myPackage;

public class Forward extends Player {
	// Atribut tambahan yang dimiliki seorang pemain "Forward".
    private int kick_power;
   
    // Constructor kosong
    public Forward() {
	    super();
    }

    // Constructor berisi
    public Forward(String name, String nationality, String club, String position, int age, int jerseyNumber, int kick_power) {
	    super(name, nationality, club, position, age, jerseyNumber);
	    this.kick_power = kick_power;
    }
   
    // Metode Abstrak dari super-class (Pemain "Forward" memiliki kriterianya tersendiri untuk batasan stamina).
    @Override
    public void genStamina() {
	    if(this.getAge()<=20) this.setStamina((int) (Math.random() * (101 - 60)) + 60 + 1); // umur<=20 -> stamina(60 sampai 101 inkulsif)
	    else if(this.getAge()<=30) this.setStamina((int) (Math.random() * (96 - 65)) + 65 + 1); // umur<=30 -> stamina(65 sampai 96 inklusif)
	    else this.setStamina((int) (Math.random() * (91 - 60)) + 60 + 1); // umur yang lebih tua -> stamina(60 sampai 91 inklusif)
    }

    // Setter AND Getter
    public int getKick_power() {
	    return kick_power;
    }

    public void setKick_power(int kick_power) {
	    this.kick_power = kick_power;
    }
}
