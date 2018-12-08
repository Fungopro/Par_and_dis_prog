package net.thumbtack.school.file;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorException;
import net.thumbtack.school.figures.v3.Point2D;
import net.thumbtack.school.figures.v3.Rectangle;
import net.thumbtack.school.ttschool.Trainee;
import net.thumbtack.school.ttschool.TrainingException;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import java.lang.Object;
import java.util.Scanner;

import org.apache.commons.io.*;


public class FileService {

    public static void  writeByteArrayToBinaryFile(String fileName, byte[] array) throws IOException {
        writeByteArrayToBinaryFile(new File(fileName),array);
    }

    public static void  writeByteArrayToBinaryFile(File file, byte[] array) throws IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
        out.write(array);
        out.close();
    }

    public static byte[]  readByteArrayFromBinaryFile(String fileName) throws IOException {
        return readByteArrayFromBinaryFile(new File(fileName));
    }

    public static byte[]  readByteArrayFromBinaryFile(File file) throws IOException {
        DataInputStream input= new DataInputStream(new FileInputStream(file));
        byte[] array = new byte[(int) file.length()];
        IOUtils.readFully(input, array);
        input.close();
        return array;
    }


        public static byte[]  writeAndReadByteArrayUsingByteStream( byte[] array) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(array);
        ByteArrayInputStream in = new ByteArrayInputStream(array);
        byte[] res=new byte[array.length/2];
        int i =0;
        while (in.available()!=0){
            res[i] = (byte) in.read(); //тут требовалось считать четные байты, так что как по мне этот вариант актуален
            in.skip(1);
            i++;
        }
        out.close();
        in.close();
        return res;
    }
    // на всякий случай
    public static byte[]  writeAndReadByteArrayUsingByteStreamREADFULLY( byte[] array) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(array);
        String str = out.toString();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        byte[] res=new byte[array.length];
        byte[] temp=new byte[array.length/2];
        IOUtils.readFully(in, res);
        for(int i=0;i<temp.length;i++){
            temp[i]=res[i*2];
        }
        out.close();
        in.close();
        return temp;
    }


    public static void  writeByteArrayToBinaryFileBuffered(String fileName, byte[] array) throws IOException {
        writeByteArrayToBinaryFileBuffered(new File(fileName), array);
    }

    public static void  writeByteArrayToBinaryFileBuffered(File file, byte[] array) throws IOException {
        BufferedOutputStream out= new BufferedOutputStream(new FileOutputStream(file));
        out.write(array);
        out.close();
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) throws IOException {
    return readByteArrayFromBinaryFileBuffered(new File(fileName));
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(File file) throws IOException {
        FileInputStream input = new FileInputStream(file);
        BufferedInputStream in= new BufferedInputStream(input);
        byte[] array = new byte[(int)file.length()];
        IOUtils.readFully(in,array);
        in.close();
        return array;
    }

    public static void  writeRectangleToBinaryFile(File file, Rectangle rect) throws IOException {
        DataOutputStream out= new DataOutputStream(new FileOutputStream(file));
        out.writeInt(rect.getTopLeft().getX());
        out.writeInt(rect.getTopLeft().getY());
        out.writeInt(rect.getBottomRight().getX());
        out.writeInt(rect.getBottomRight().getY());
        out.close();
    }

    public static Rectangle  readRectangleFromBinaryFile(File file) throws IOException, ColorException {
        DataInputStream in= new DataInputStream(new FileInputStream(file));
        return new Rectangle(
                new Point2D(in.readInt(),in.readInt()),
                new Point2D(in.readInt(),in.readInt()),Color.RED );
    }


    public static void  writeRectangleArrayToBinaryFile(File file, Rectangle[] rects ) throws IOException {
        DataOutputStream out= new DataOutputStream(new FileOutputStream(file));
        for(Rectangle rect: rects){
        out.writeInt(rect.getTopLeft().getX());
        out.writeInt(rect.getTopLeft().getY());
        out.writeInt(rect.getBottomRight().getX());
        out.writeInt(rect.getBottomRight().getY());
        }
        out.close();
    }

    public static Rectangle[]  readRectangleArrayFromBinaryFileReverse(File file) throws IOException, ColorException {
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        Rectangle[] rects= new Rectangle[(int)file.length()/16];
        for (int i =0; i<file.length(); i+=16)
        rects[rects.length - i/16 -1] = new Rectangle(
                new Point2D(in.readInt(),in.readInt()),
                new Point2D(in.readInt(),in.readInt()),
                Color.RED);
        return rects;
    }

    public static void  writeRectangleToTextFileOneLine(File file, Rectangle rect) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(rect.getTopLeft().getX()+" "+rect.getTopLeft().getY()+" "+rect.getBottomRight().getX()+" "+rect.getBottomRight().getY());
        bw.close();
    }

    public static Rectangle  readRectangleFromTextFileOneLine(File file) throws IOException, ColorException {
        Scanner scan = new Scanner(new FileInputStream(file));

        int[] temp = new int [4];
        for (int i = 0; i<temp.length;i++)
        try {
            temp[i] = scan.nextInt();
        } catch (NumberFormatException ignored) {}

        return new Rectangle(temp[0],temp[1],temp[2],temp[3],Color.RED);
    }

    public static void  writeRectangleToTextFileFourLines(File file, Rectangle rect) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(file));
        printWriter.println(rect.getTopLeft().getX());
        printWriter.println(rect.getTopLeft().getY());
        printWriter.println(rect.getBottomRight().getX());
        printWriter.println(rect.getBottomRight().getY());
        printWriter.close();
    }
    public static Rectangle  readRectangleFromTextFileFourLines(File file) throws IOException, ColorException {
        return readRectangleFromTextFileOneLine(file);
    }

    public static void  writeTraineeToTextFileOneLine(File file, Trainee trainee) throws IOException {
        // BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        // bw.write(trainee.getFirstName()+" "+trainee.getLastName()+" "+trainee.getRating());
        // bw.close();
        PrintWriter out = new PrintWriter(new FileOutputStream(file));
        out.print(trainee.getFirstName()+" "+trainee.getLastName()+" "+trainee.getRating());
        out.close();
    }


    public static Trainee  readTraineeFromTextFileOneLine(File file) throws IOException, TrainingException {
        return readTraineeFromTextFileThreeLines(file);
    }

    public static void  writeTraineeToTextFileThreeLines(File file, Trainee trainee) throws IOException {
        PrintWriter  out = new PrintWriter (new FileOutputStream(file));
        out.println(trainee.getFirstName());
        out.println(trainee.getLastName());
        out.println(trainee.getRating());
        out.close();
    }

    public static Trainee  readTraineeFromTextFileThreeLines(File file) throws IOException, TrainingException {
        Scanner scan = new Scanner(new FileReader(file));
        return new Trainee(scan.next(),scan.next(),scan.nextInt());
    }

    public static void  serializeTraineeToBinaryFile(File file, Trainee trainee) throws IOException {
        FileOutputStream out = new FileOutputStream(file);
        Gson gson = new Gson();
        out.write(gson.toJson(trainee).getBytes());
        out.close();
    }

    public static Trainee  deserializeTraineeFromBinaryFile(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        Gson gson = new Gson();
        byte[] array = new byte[(int)file.length()];
        IOUtils.readFully(in,array);
        String str = new String(array, StandardCharsets.UTF_8);
        in.close();
        return (Trainee)gson.fromJson(str,Trainee.class);
    }

    public static String  serializeTraineeToJsonString(Trainee trainee){
        return new Gson().toJson(trainee);
    }

    public static Trainee  deserializeTraineeFromJsonString(String json){
        return new Gson().fromJson(json,Trainee.class);
    }

    public static void  serializeTraineeToJsonFile(File file, Trainee trainee) throws IOException {
    PrintWriter out = new PrintWriter(new FileWriter(file));
    out.print(new Gson().toJson(trainee));
    out.close();
    }

    public static Trainee  deserializeTraineeFromJsonFile(File file) throws IOException {
        Scanner in = new Scanner(new FileReader(file));
        String str =in.nextLine();
        in.close();
        return new Gson().fromJson(str, Trainee.class);
    }

}
