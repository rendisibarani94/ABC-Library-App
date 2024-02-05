package perpustakaan.abcd;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PerpusDriver{
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

    private static void clearScreen(){
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } catch (Exception ex){
            System.err.println("tidak bisa clear screen");
        }
    }

    public static void main(String[] args) throws IOException {
        boolean isLanjut  = true;
        Buku bukubfr  =new Buku();
        Anggota Rendi = new Anggota(bukubfr);
        History history = new History();
        KartuAnggota kartuAnggota = new KartuAnggota(Rendi.getId(), Rendi.getNama());
        Rendi.setKartu(kartuAnggota);
        Rendi.setHistory(history);
        Pengurus Admin = new Pengurus(314210, "Lely Tambunan");
        Pengumuman pengumuman = new Pengumuman();
            do {
                try {
                clearScreen();
                System.out.println("===============================================");
                System.out.println("===== SISTEM INFORMASI PPERPUSTAKAAN ABCD =====");
                System.out.println("==================  WELCOME!!  ================");
                Scanner starting = new Scanner(System.in);
                System.out.println("1. Login as Member.");
                System.out.println("2. Member Register.");
                System.out.println("3. Login as Admin.");
                System.out.print("Masukkan Pilihan Anda :");

                int start = starting.nextInt();
                switch (start) {
                    case 1:
                        clearScreen();
                        if(Rendi.getUsername() == null) {
                            System.out.println("Anda belum terdaftar, Silahkan mendaftar dahulu ..");
                            Rendi.register();
                        }
                        clearScreen();
                        Rendi.login(Admin);
                        break;
                    case 2:
                        clearScreen();
                        if(Rendi.getUsername() != null){
                            System.out.println("Akun anda sudah terdaftar !");
                            if(getYesorNo("Apakah Anda Ingin Lanjut Login ") == true){
                                clearScreen();
                                Rendi.login(Admin);
                            }
                        }
                        Rendi.register();
                        if(getYesorNo("Apakah Anda Ingin Lanjut Login ") == true){
                            clearScreen();
                            Rendi.login(Admin);
                        }
                        break;
                    case 3:
                        clearScreen();
                        Admin.login(Rendi);
                        break;
                    default:
                        System.err.println("\nInput anda tidak ditemukan\nSilahkan pilih [1-4]");
                    }
                isLanjut = getYesorNo("Apakah Anda ingin LOGOUT (y => LOGOUT), ( x=> ExitProgram)");
                }catch (InputMismatchException e){
                    System.err.println("Terjadi error : Input Anda Seharusnya Dalam Bentuk Angka. Anda Kembali Ke Halaman Beranda.");
                    clearScreen();
                    isLanjut = getYesorNo("Apakah Anda Ingin Melanjutkan (y => Melanjutkan Program), ( x=> ExitProgram)");
                }
            }while(isLanjut == true);

    }
}