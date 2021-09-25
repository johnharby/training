package com.learn.algorithms2;

import edu.princeton.cs.algs4.Picture;
import java.io.File;
import java.util.Arrays;

public class SeamCarver {
    private static final boolean VERTICAL = true, HORIZONTAL = false;
    private static final double BORDER_ENERGY = 1000d;
    private Picture picture;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException("Constructor called with null argument.");
        }
        this.picture = new Picture(picture);
    }

    // current picture
    public Picture picture() {
        return new Picture(this.picture);
    }

    // width of current picture
    public int width() {
        return picture.width();
    }

    // height of current picture
    public int height() {
        return picture.height();
    }

    // energy of pixel at column x and row y
    public  double energy(int col, int row) {
        if (!isValidPixel(col, row)) {
            throw new IllegalArgumentException("Pixel given is invalid: col: " + col + ", row: " + row );
        }
        if (col == 0 || col == width() -1 || row == 0 || row == height() - 1) {
            return BORDER_ENERGY;
        }

        double hred = picture.get(col + 1, row).getRed() - picture.get(col - 1, row).getRed();
        double vred = picture.get(col, row + 1).getRed() - picture.get(col, row - 1).getRed();

        double hgrn = picture.get(col + 1, row).getGreen() - picture.get(col - 1, row).getGreen();
        double vgrn = picture.get(col, row + 1).getGreen() - picture.get(col, row - 1).getGreen();

        double hblue = picture.get(col + 1, row).getBlue() - picture.get(col - 1, row).getBlue();
        double vblue = picture.get(col, row + 1).getBlue() - picture.get(col, row - 1).getBlue();

        return Math.sqrt(hred * hred + vred * vred + hgrn * hgrn + vgrn * vgrn + hblue * hblue + vblue * vblue);
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        Tuple[][] energyGrid = initBorders();
        for (int col = 1; col < width(); col++) {
            for (int row = 0; row < height(); row++) {
                double curEnergy = energy(col, row);
                Tuple[] paths = {
                        new Tuple( isValidPixel(col - 1, row - 1) ? curEnergy + energyGrid[row - 1][col - 1].energy : Double.MAX_VALUE, row - 1),
                        new Tuple( isValidPixel(col - 1, row) ? curEnergy + energyGrid[row][col - 1].energy : Double.MAX_VALUE, row),
                        new Tuple( isValidPixel(col - 1, row + 1) ? curEnergy + energyGrid[row + 1][col - 1].energy : Double.MAX_VALUE, row + 1)
                };
                Arrays.sort(paths);
                energyGrid[row][col] = paths[0];
            }
        }
        return getLowestEnergyHorizStream(energyGrid);
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        Tuple[][] energies = initBorders();
        for (int i = 1; i < height(); ++i) {
            for (int j = 0; j < width(); ++j) {
                double curEnergy = energy(j, i);
                Tuple[] tupleArr = getTupleArr(energies, i, j, curEnergy);
                Arrays.sort(tupleArr);
                energies[i][j] = tupleArr[0];
            }
        }
        return getLowestEnergyVertStream(energies);
    }

    private Tuple[] getTupleArr(Tuple[][] energies, int row, int col, double curEnergy) {
        Tuple[] ret = { new Tuple( isValidPixel(col - 1, row -1) ? curEnergy + energies[row - 1][col - 1].energy : Double.MAX_VALUE, col - 1),
                new Tuple( isValidPixel(col, row - 1) ? curEnergy + energies[row - 1][col].energy : Double.MAX_VALUE, col),
                new Tuple( isValidPixel(col + 1, row - 1) ? curEnergy + energies[row - 1][col + 1].energy : Double.MAX_VALUE, col + 1)
        };
        return ret;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (!isValidSeam(seam, false)) {
            throw new IllegalArgumentException("Seam given is not valid");
        }
        Picture pic = getHorizInnerPic(seam);
        this.picture = pic;
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (!isValidSeam(seam, VERTICAL)) {
            throw new IllegalArgumentException("Seam given is not valid");
        }
        Picture innerPic = getVertInnerPic(seam);
        this.picture = innerPic;
    }

    private Tuple[][] initBorders() {
        Tuple[][] energies = new Tuple[height()][width()];
        for (int i = 0; i < height(); ++i) {
            energies[i][0] = new Tuple(BORDER_ENERGY, -1);
        }
        for (int i = 1; i < width(); ++i) {
            energies[0][i] = new Tuple(BORDER_ENERGY, -1);
        }
        return energies;
    }

    private Picture getHorizInnerPic(int[] seam) {
        Picture innerPic = new Picture(width(), height() - 1);
        for (int i = 0; i < width(); i++) {
            int c = 0;
            for (int j = 0; j < height() - 1; j++) {
                if (seam[i] == j) {
                    c = 1;
                }
                innerPic.set(i, j, picture.get(i, j + c));
            }
        }
        return innerPic;
    }

    private Picture getVertInnerPic(int[] seam) {
        Picture innerPic = new Picture(width() - 1, height());
        for(int i = 0; i < height(); i++) {
            int c = 0;
            for(int j = 0; j < width() - 1; j++) {
                if (seam[i] == j) {
                    c = 1;
                }
                innerPic.set(j, i, picture.get(j + c, i));
            }
        }
        return innerPic;
    }

    private boolean isValidPixel(int x, int y) {
        return x > -1  && y > -1 && x < width() && y < height();
    }

    private int[] getLowestEnergyVertStream(Tuple[][] energies) {
        int[] seam = new int[height()];
        double lowestEnergy = Double.MAX_VALUE;
        int index = -1;
        for (int col = 0; col < width(); col++) {
            if (energies[height() - 1][col].energy < lowestEnergy) {
                lowestEnergy = energies[height() - 1][col].energy;
                index = col;
            }
        }
        int row = height() - 1;
        while (row > -1) {
            seam[row] = index;
            index = energies[row][index].prev;
            row--;
        }
        return seam;
    }

    private int[] getLowestEnergyHorizStream(Tuple[][] energies) {
        int[] seam = new int[width()];
        double lowestEnergy = Double.MAX_VALUE;
        int index = -1;
        for (int row = 0; row < height(); row++) {
            if (energies[row][width() - 1].energy < lowestEnergy) {
                lowestEnergy = energies[row][width() - 1].energy;
                index = row;
            }
        }

        int col = width() - 1;
        while (col > -1) {
            seam[col] = index;
            index = energies[index][col].prev;
            col--;
        }
        return seam;
    }


    private boolean isValidSeam(int[] seam, boolean vert) {
        if (seam == null || (vert && seam.length != height()) ||
                (!vert && seam.length != width())) {
            return false;
        }
        for(int i : seam) {
            if ((i < 0 ) || (vert && i >= width()) || (!vert && i>= height())) {
                return false;
            }
        }
        for (int i = 0; i < seam.length - 1; ++i) {
            if (Math.abs(seam[i] - seam[i + 1]) > 1) {
                return false;
            }
        }
        return true;
    }


    private static class Tuple implements Comparable<Tuple>{
        public final int prev;
        public final double energy;

        public Tuple(double energy, int prev) {
            this.prev = prev;
            this.energy = energy;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Tuple)) {
                return false;
            }
            Tuple otherTuple = (Tuple) other;
            return otherTuple.energy == this.energy;
        }

        @Override
        public int hashCode() {
            int ret = 17;
            int val = (int) energy;
            ret = 31*val;
            return ret;
        }

        @Override
        public int compareTo(Tuple other) {
            return Double.compare(this.energy, other.energy);
        }
    }

    public static void main(String[] args) {
        final String fileName = "/Users/johnharby/6x5.png";
        Picture picture = new Picture(new File(fileName));
        SeamCarver sc = new SeamCarver(picture);
        System.out.println(sc.energy(1, 1));
        System.out.println(sc.width());
        System.out.println(sc.height());
        int[] seam = {2, 2, 1, 2, 1, 2};
        sc.removeHorizontalSeam(seam);
        System.out.println(sc.width());
        System.out.println(sc.height());
        System.out.println(sc.energy(1, 1));
        int[] hseam = sc.findHorizontalSeam();
        Arrays.stream(hseam).forEach(e->System.out.print(e + " "));
        System.out.println("");
        int[] vseam = sc.findVerticalSeam();
        Arrays.stream(vseam).forEach(e->System.out.print(e + " "));
    }

}