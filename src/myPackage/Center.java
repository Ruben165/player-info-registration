package myPackage;

public class Center extends Player {
    // Atribut tambahan yang dimiliki seorang pemain "Midfielder".
    private int pass_accuracy;
   
    // Constructor kosong
    public Center() {
	    super();
    }

    // Constructor berisi
    public Center(String name, String nationality, String club, String position, String player_id, int age, int jerseyNumber, int pass_accuracy) {
	    super(name, nationality, club, position, player_id, age, jerseyNumber);
	    this.pass_accuracy = pass_accuracy;
    }
   
    // Metode Abstrak dari super-class (Pemain "Center" memiliki kriterianya tersendiri dalam menentukan stamina).
    @Override
    public void genStamina() {
	    if(this.getAge()<=20) this.setStamina((int) (Math.random() * (96 - 70)) + 70 + 1); // umur<=20 -> stamina(70 sampai 96 inklusif)
	    else if(this.getAge()<=30) this.setStamina((int) (Math.random() * (101 - 72)) + 72 + 1); // umur<=30 -> stamina(72 sampai 101 inklusif)
	    else this.setStamina((int) (Math.random() * (96 - 60)) + 60 + 1); // umur yang lebih tua -> stamina(60 sampai 96 inklusif)
    }

    // Setter AND Getter
    public int getPass_accuracy() {
	    return pass_accuracy;
    }

    public void setPass_accuracy(int pass_accuracy) {
	    this.pass_accuracy = pass_accuracy;
    }
}
