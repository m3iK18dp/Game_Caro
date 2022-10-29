package caro_console;

import java.util.Scanner;

public class GameCaro {
    private static int soDong;
    private static int soCot;
    private static final char[] quanCo = { 'X', 'O' };
    private static char[][] banCo;
    static Scanner scanner = new Scanner(System.in);

    public GameCaro(int soDong, int soCot) {
        banCo = new char[soDong + 2][soCot + 2];
        int ngChoi = 0;
        boolean c = true;
        while (c) {
            int dong = -1, cot = -1;
            do {
                System.out.println("Người chơi " + (ngChoi % 2 + 1) + ", mời nhập vị trí: ");
                System.out.print("Dòng số: ");
                dong = scanner.nextInt();
                System.out.print("Cột số: ");
                cot = scanner.nextInt();
                if (dong >= 1 && dong <= soDong && cot >= 1 && cot <= soCot)
                    if (banCo[dong][cot] == '\u0000') {
                        banCo[dong][cot] = quanCo[ngChoi % 2];
                        break;
                    }
            } while (true);
            show();
            if (checkWin(dong, cot, quanCo[ngChoi % 2])) {
                System.out.println("Người chơi " + (ngChoi % 2 + 1) + " chiến thắng.");
                c = false;
            }
            ngChoi++;
        }
        if (c)
            System.out.println("Hòa nhau ^-^");
    }

    public static void show() {
        System.out.printf("%-2S", "");
        for (int i = 1; i <= soCot; i++)
            System.out.printf("%-2d", i);
        System.out.println();
        for (int i = 1; i <= soDong; i++) {
            System.out.printf("%-2d", i);
            for (int j = 1; j <= soCot; j++)
                System.out.printf("%-2c", banCo[i][j] == '\u0000' ? '_' : banCo[i][j]);
            System.out.println();
        }
    }

    public static boolean check(char[][] checkF) {
        for (char[] arrC : checkF)
            for (char c : arrC)
                if (c == '\u0000')
                    return false;
        return true;
    }

    public static boolean checkWin(int x, int y, char co) {
        // Kiem tra hang ngang
        for (int i = y - 4 >= 1 ? y - 4 : 1, dem = 0; i <= (y + 4 <= soCot ? y + 4 : soCot); i++)
            if (banCo[x][i] == co) {
                dem++;
                if (dem >= 5)
                    return true;
            } else
                dem = 0;
        // Kiem tra hang doc
        for (int i = x - 4 >= 1 ? x - 4 : 1, dem = 0; i <= (x + 4 <= soDong ? x + 4 : soDong); i++)
            if (banCo[x][i] == co) {
                dem++;
                if (dem >= 5)
                    return true;
            } else
                dem = 0;
        // Kiem tra hang song song voi duong cheo chinh
        int stX = x - 4, stY = y - 4;
        while (stX < 1 || stY < 1) {
            stX++;
            stY++;
        }
        int edX = x + 4, edY = y + 4;
        while (edX > soDong || edY > soCot) {
            edX--;
            edY--;
        }
        for (int i = stX, j = stY, dem = 0; i <= edX; i++, j++)
            if (banCo[i][j] == co) {
                dem++;
                if (dem >= 5)
                    return true;
            } else
                dem = 0;
        // Kiem tra hang song song voi duong cheo phu
        stX = x + 4;
        stY = y - 4;
        while (stX > soDong || stY < 1) {
            stX--;
            stY++;
        }
        edX = x - 4;
        edY = y + 4;
        while (edX < 1 || edY > soCot) {
            edX++;
            edY--;
        }
        for (int i = stX, j = stY, dem = 0; i >= edX; i--, j++)
            if (banCo[i][j] == co) {
                dem++;
                if (dem >= 5)
                    return true;
            } else
                dem = 0;
        return false;
    }

    public static void main(String[] args) {
        System.out.print("Nhập số Dòng:  ");
        soDong = scanner.nextInt();
        System.out.print("Nhập số Cột:   ");
        soCot = scanner.nextInt();
        new GameCaro(soDong, soCot);
    }
}