package perpustakaan.abcd;

public class KartuAnggota extends Anggota{
    public Role role = Role.anggota;
    public String nama;
    public int id;

    public KartuAnggota(int id, String nama){
        super(id, nama);
        super.role = Role.anggota;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getNama() {
        return super.getNama();
    }

    private static void clearScreen(){
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } catch (Exception ex){
            System.err.println("tidak bisa clear screen");
        }
    }

    public String cetakKartuAnggota(){
        clearScreen();
        return  "\n==== KARTU ANGGOTA PERPUSTAKAAN ABCD ====\n" +
                "Nama : " + this.nama+
                "\nID   : " + this.id +
                "\nRole : " + this.role +
                "\n=========================================" +"\n";
    }
}