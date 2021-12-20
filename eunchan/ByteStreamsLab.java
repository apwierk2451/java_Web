package eunchan;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class ByteStreamsLab {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		System.out.print("���� ���� �̸��� �Է��Ͻÿ�: ");
		String inputFileName = scan.next();
		
		System.out.print("���� ���� �̸��� �Է��Ͻÿ�: ");
		String outputFileName = scan.next();
		
		try (InputStream inputStream = new FileInputStream(inputFileName);
				OutputStream outputStream = new FileOutputStream
						(outputFileName)) {
			int c;
			
			while ((c = inputStream.read()) != -1) {
				outputStream.write(c);
			}
		}
		System.out.println(inputFileName +"�� " + outputFileName + "�� �����Ͽ����ϴ�. ");
	}

}
