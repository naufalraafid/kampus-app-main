import java.util.Scanner;

import model.Mahasiswa;
import repository.Database;
import model.Prodi;
public class KampusApp {
    public static void main(String[] arg) {
        KampusApp kampusApp = new KampusApp();
        kampusApp.showMenu();
    }

    private Scanner scanner;
    private Database db;

    public KampusApp() {
        scanner = new Scanner(System.in);
        db = new Database();
    }

    public void showMenu() {
        System.out.println("---------------------------------");
        System.out.println("Selamat Datang di Aplikasi Kampus");
        System.out.println("Pilihan Menu:");
        System.out.println("1 -> Tambah Data Mahasiswa");
        System.out.println("2 -> Ubah Data Mahasiswa");
        System.out.println("3 -> Hapus Data Mahasiswa");
        System.out.println("4 -> Cari Data Mahasiswa");
        System.out.println("5 -> Tambah Data Prodi Mahasiswa");
        System.out.println("0 -> Keluar Aplikasi");
        System.out.print("Silahkan masukan menu yang dipilih: ");
        int menuYangDipilih = scanner.nextInt();
        scanner.nextLine();
        switch(menuYangDipilih) {
            case 1:
            menuTambah();
            break;
            case 2:
            menuUbah();
            break;
            case 3:
            menuHapus();
            break;
            case 4:
            menuCari();
            break;
            case 5:
            menuTambahProdi();
            break;
            default: {
                System.out.print("* Terimakasih sudah menggunakan Aplikasi Kampus *");
                System.exit(0);
            }
            break;
        }
        scanner.close();
    }

    public void menuTambah() {
        System.out.println("----- Menu Tambah Mahasiswa -----");
        System.out.print("Masukan NIM: ");
        String nim = scanner.nextLine();
        System.out.print("Masukan Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan ID Prodi: ");
        String idProdi = scanner.nextLine();
        Prodi prodi = db.prodiTbl.getProdiById(idProdi);
        if (prodi == null) {
            System.out.println("* ID Prodi tidak ditemukan *");
        } else {
            Mahasiswa mahasiswa = new Mahasiswa(nim, nama, prodi);
            db.mahasiswaTbl.create(mahasiswa);
            System.out.println("* Data berhasil ditambahkan *");
        }
        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
    }

    public void menuUbah() {
        System.out.println("----- Menu Ubah Data Mahasiswa -----");
        System.out.print("Masukan NIM Sebelumnya: ");
        String nimLama = scanner.nextLine();
        System.out.print("Masukan NIM: ");
        String nimBaru = scanner.nextLine();
        System.out.print("Masukan Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan ID Prodi: ");
        String idProdi = scanner.nextLine();
        Prodi prodi = db.prodiTbl.getProdiById(idProdi);
        if (prodi == null) {
            System.out.println("* ID Prodi tidak ditemukan *");
        } else {
            Mahasiswa mahasiswa = new Mahasiswa(nimBaru, nama, prodi);
            db.mahasiswaTbl.update(nimLama, mahasiswa);
            System.out.println("* Data berhasil diubah *");
        }
        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
    }

    public void menuCari(){
        System.out.println("----- Menu Cari Data Mahasiswa -----");
        System.out.print("Masukan NIM: ");
        String input = scanner.nextLine();
        Mahasiswa mahasiswa = db.mahasiswaTbl.getMahasiswaByNim(input);
        if (mahasiswa == null) {
            System.out.println("* Data tidak ditemukan *");
        } 
        else {
            System.out.println("* NIM: " + mahasiswa.getNim() + " *");
            System.out.println("* Nama: " + mahasiswa.getNama() + " *");
            if (mahasiswa.getProdi() != null) {
                System.out.println("* Prodi ID: " + mahasiswa.getProdi().getidProdi() + " *");
                System.out.println("* Prodi Nama: " + mahasiswa.getProdi().getnamaProdi() + " *");
            }
        }
        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
    }

    public void menuHapus() {
        System.out.println("----- Menu Hapus Data Mahasiswa -----");
        System.out.print("Masukan NIM: ");
        String nim = scanner.nextLine();
        db.mahasiswaTbl.delete(nim);
        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
    }

    public void menuTambahProdi() {
        System.out.println("----- Menu Tambah Prodi -----");
        System.out.print("Masukan ID Prodi: ");
        String idProdi = scanner.nextLine();
        System.out.print("Masukan Nama Prodi: ");
        String namaProdi = scanner.nextLine();
        Prodi prodi = new Prodi(idProdi, namaProdi);
        db.prodiTbl.create(prodi);
        System.out.println("* Prodi berhasil ditambahkan *");
        System.out.println("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
        showMenu();
    }
}