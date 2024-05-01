package src.FileParser; // Paket yang sesuai dengan struktur direktori

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    private int sum;
    private int maxlength;
    private List<String> fileContent;

    public FileParser() {
        this.sum = 0;
        this.maxlength = 0;
    }

    public int getSum() {
        return this.sum;
    }

    public int getMax() {
        return this.maxlength;
    }
    public void displayInformation(){
        int count = 0;
        for (int i = 0; i <= getMax(); i++) {
            count = 0;
            for (String line : this.fileContent) {
                if (line.length() == i) {
                    count++;

                }
            }
            System.out.println("Kata dengan panjang " + i + " ada : " + count);

        }
        System.out.println("Total kata ada  " + getSum());
    }

    public List<String> readFile(String fileName, int minLength, int maxLength) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() <= maxLength && line.length() >= minLength) {
                    lines.add(line);
                    this.sum++;
                    if (line.length() > getMax()) {
                        this.maxlength = line.length();
                    }

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.fileContent = lines;
        return lines;
    }
}
