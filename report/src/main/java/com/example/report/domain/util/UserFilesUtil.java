package com.example.report.domain.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class UserFilesUtil {

  // optional directory: /report/src/main/resources/static/images/users/avatars
  private static final String USER_FILE_AVATAR_PATH = "/avatar";
  private static final String USER_FILE_THEME_PATH = "/theme";

  private static Random random = new Random();

  /**
   * CREATE USER AVATAR
   * 
   * @param basePath
   * @param avatarBytes
   * @return
   */
  public static String createUserAvatar(String basePath, String userIdentifier, InputStream avatarBytes) {
    return saveImageFile(basePath, userIdentifier, USER_FILE_AVATAR_PATH, avatarBytes);
  }

  /**
   * CREATE USER THEME IMAGE
   * 
   * @param basePath
   * @param themeImageBytes
   * @return
   */
  public static String createUserThemeImage(String basePath, String userIdentifier, InputStream themeImageBytes) {
    return saveImageFile(basePath, userIdentifier, USER_FILE_THEME_PATH, themeImageBytes);
  }

  /**
   * GET USER AVATAR
   * 
   * @param avatarPath
   * @return
   * @throws IOException
   */
  public static byte[] getUserAvatar(String avatarPath) throws IOException {
    return getImageFile(avatarPath);
  }

  /**
   * GET USER THEME IMAGE
   * 
   * @param themeImagePath
   * @return
   * @throws IOException
   */
  public static byte[] getUserThemeImage(String themeImagePath) throws IOException {
    return getImageFile(themeImagePath);
  }

  /**
   * SAVE IMAGE FILE
   * 
   * @param basePath
   * @param directory
   * @param imageBytes
   * @return
   */
  private static String saveImageFile(String basePath, String userIdentifier, String directory, InputStream imageBytes) {
    // Ensure InputStream is buffered
    imageBytes = buffered(imageBytes);
    // Calculate target directory
    String targetDirectory = getTargetDirectory(basePath, directory, userIdentifier);
    // Calculate filename
    String pdfFilePath = String.format(targetDirectory);
    // Write the bytes to file
    writeFile(pdfFilePath, imageBytes);
    // Return the file path
    return pdfFilePath;
  }

  /**
   * GET IMAGE FILE
   * 
   * @param avatarPath
   * @return
   * @throws IOException
   */
  private static byte[] getImageFile(String avatarPath) throws IOException {
    File imageFile = new File(avatarPath);

    if (imageFile.exists()) {
      return writeFileBytes(avatarPath);
    } else {
      System.out.println("Image file not found.");
      throw new IOException("Image file not found");
    }
  }

  /**
   * GET TARGET DIRECTORY
   * 
   * @param basePath
   * @param subDirectory
   * @return
   */
  private static String getTargetDirectory(String basePath, String subDirectory, String userIdentifier) {
    String fileName;

    // Generate a name for the target file
    fileName = generateRandomFileName() + ".png";

    Path base = Paths.get(basePath);
    Path target = base
        .resolve(subDirectory)
        .resolve(userIdentifier);

    // Create target directory
    if (!Files.exists(target)) {
      try {
        Files.createDirectories(target);
      } catch (IOException e) {
        System.out.println("Failed to create target directory: " + e.getMessage());
      }
    } else {
      System.out.println("Target directory already exists: " + target);
    }

    // Create target file
    if (Files.exists(target.resolve(fileName))) {
      fileName = generateRandomFileName() + ".png";
    }

    try {
      Files.createFile(target.resolve(fileName));
    } catch (IOException e) {
      System.out.println("Failed to create target file: " + e.getMessage());
      // throw new RuntimeException(e);
    }

    return target.resolve(fileName).toString();
  }

  /**
   * WRITE FILE BYTES TO FILE PATH AND RETURN FILE PATH AS STRING
   * 
   * @param filePath
   * @param content
   * @return
   */
  private static String writeFile(String filePath, InputStream content) {
    try {
      FileOutputStream outputStream = new FileOutputStream(filePath);
      byte[] buffer = new byte[1024];
      int bytesRead;
      while ((bytesRead = content.read(buffer)) != -1) {
        outputStream.write(buffer, 0, bytesRead);
      }
      outputStream.close();
      content.close();
      System.out.println("File written successfully: " + filePath);
      return content.toString();
    } catch (Exception e) {
      System.out.println("Failed to write file: " + e.getMessage());
    }
    return null;
  }

  /**
   * WRITE FILE BYTES
   * 
   * @param filePath
   * @return
   * @throws IOException
   */
  public static byte[] writeFileBytes(String filePath) throws IOException {
    Path file = Paths.get(filePath);
    return Files.readAllBytes(file);
  }

  /**
   * GENERATE RANDOM FILE NAME OF LENGTH 10 CHARACTERS LONG (INTEGER) AND RETURN AS STRING
   * 
   * @return
   */
  public static String generateRandomFileName() {
    int randomInt = Math.abs(random.nextInt(10));
    return Integer.toString(randomInt);
  }

  /**
   * BUFFER INPUT STREAM
   * 
   * @param inputStream
   * @return
   */
  private static BufferedInputStream buffered(InputStream inputStream) {
    if (inputStream instanceof BufferedInputStream) {
      return (BufferedInputStream) inputStream;
    } else {
      return new BufferedInputStream(inputStream);
    }
  }

}
