package perpustakaan.abcd;

public enum PengurusRole {
    petugasHarian, petugasKebersihan;

    @Override
    public String toString() {
        switch (this){
            case petugasKebersihan :
                return "Petugas Kebersihan";
            default:
                return "Pengurus Harian";
        }
    }
}
