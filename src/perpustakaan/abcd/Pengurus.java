package perpustakaan.abcd;

import java.util.Scanner;

public class Pengurus extends Cetak{
    public int id;
    public String nama;
    private String username = "Admin";
    private String password = "Admin123";

    Role role = Role.pengurus;

    PengurusRole roleDetail = PengurusRole.petugasHarian;

    Pengumuman pengumuman;

    public void setPengumuman(Pengumuman pengumuman) {
        this.pengumuman = pengumuman;
    }

    public void getPengumuman() {
        pengumuman.display();
    }

    public void setRoleHarian() {
        this.roleDetail = PengurusRole.petugasHarian;
    }

    public void setRoleKebersihan() {
        this.roleDetail = PengurusRole.petugasKebersihan;
    }

    //Constructor
    public Pengurus(int id, String nama){
        this.id = id;
        this.nama = nama;
    }
    public Pengurus(){

    }

    public void login(Anggota anggota){
        System.out.println("==== Admin Login ====");
        String uname;
        String pword;
        System.out.print("Masukkan Username : ");
        Scanner terminalInput = new Scanner(System.in);
        uname = terminalInput.nextLine();
        System.out.print("Masukkan Password : ");
        pword = terminalInput.nextLine();

        if (this.username.equals(uname) && this.password.equals(pword)){
            this.mainMenuAdmin(anggota);
        }else {
            clearScreen();
            System.out.println("Autentikasi Gagal, Silahkan Periksa Kembali Username dan Password Anda");
            this.login(anggota);
        }
    }
    public void cetak(){
        clearScreen();
        System.out.println("===== Kartu Kepengurusan Perpustakaan ABCD =====");
        System.out.println("Id Perpustakaan : "+this.id);
        System.out.println("Nama            : "+this.nama);
        System.out.println("Role            : "+role);
        System.out.println("Role Detail     : "+roleDetail);
        System.out.println("================================================");
    }

    public void mainMenuAdmin(Anggota anggota){
        Scanner terminalInput = new Scanner(System.in);
        boolean isLanjutkan = true;
        do {
            clearScreen();
                if (anggota.getNama() == null){
                    System.out.println("Belum ada Feedback dari Anggota Perpustakaan");
                }else {
                    System.out.println("\n===== FEEDBACK USER =====");
                    try {
                        System.out.println("Feedback dari User "+anggota.getNama()+" : ");
                        anggota.feedback.display();
                    }catch (Exception e){
                        System.out.println("Belum ada Feedback dari Anggota Perpustakaan");;
                    }
                    System.out.println("===== EOF FEEDBACK =====\n");
                }
            System.out.println("===== Admin Access =====\n");
            System.out.println("1. Mengolah Buku");
            System.out.println("2. Membuat pengumuman");
            System.out.println("3. Cetak Kartu Pengurus");

            System.out.print("\n\nPilihan anda: ");
            int pilihanUser = terminalInput.nextInt();
            switch (pilihanUser){
                case 1:
                    boolean lanjut = true;
                    do {
                    clearScreen();
                    System.out.println("\n=================");
                    System.out.println("  MENGOLAH BUKU  ");
                    System.out.println("=================");
                    System.out.println("1. Tambah Buku ");
                    System.out.println("2. Edit Buku ");
                    System.out.println("3. Delete Buku");
                    int pil = terminalInput.nextInt();
                    switch (pil){
                        case 1:
                            anggota.add(anggota.bukubfr);
                            break;
                        case 2:
                            anggota.update();
                            break;
                        case 3:
                            anggota.delete();
                            break;
                        default:
                            System.err.println("\nInput anda tidak ditemukan\nSilahkan pilih [1-3]");
                    }
                    lanjut = getYesorNo("Apakah Anda ingin melanjutkan Mengolah Buku");
                    }while(lanjut == true);
                    break;
                case 2:
                    clearScreen();
                    System.out.println("\n==================");
                    System.out.println("MEMBUAT PENGUMUMAN");
                    System.out.println("==================");
                    try{
                        Pengumuman pengumuman = new Pengumuman();
                        setPengumuman(pengumuman);
                        this.pengumuman.setPengumuman();
                        System.out.println("\nPengumuman Telah Berhasil Dibuat.");
                    }catch (Exception e){
                        System.err.println("Gagal Membuat Pengumuman!");
                    }
                    break;
                case 3:
                    cetak();
                    break;
                default:
                    System.err.println("\nInput anda tidak ditemukan\nSilahkan pilih [1-2]");
                    break;
            }
            isLanjutkan = getYesorNo("Apakah Anda ingin melanjutkan");
        }while(isLanjutkan == true);
    }

    private static void clearScreen(){
        try {
            if (System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception ex){
            System.err.println("tidak bisa clear screen");
        }
    }
    private static boolean getYesorNo(String message){
        Scanner terminalInput = new Scanner(System.in);
        System.out.print("\n"+message+" (y/n)? ");
        String pilihanUser = terminalInput.next();

        while(!pilihanUser.equalsIgnoreCase("y") && !pilihanUser.equalsIgnoreCase("n")) {
            System.err.println("Pilihan anda bukan y atau n");
            System.out.print("\n"+message+" (y/n)? ");
            pilihanUser = terminalInput.next();
        }

        return pilihanUser.equalsIgnoreCase("y");

    }

}                                                                                                                  