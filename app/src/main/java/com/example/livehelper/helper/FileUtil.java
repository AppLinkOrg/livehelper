package com.zrkaxt.testmachine.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.zrkaxt.testmachine.httphelper.DataHandle;
import com.zrkaxt.testmachine.obj.OutputResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
    //
    private static FileUtil Instance;

    private FileUtil() {
    }


    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    public Context ctx;

    public static FileUtil GetInstance() {
        if (FileUtil.Instance == null) {
            FileUtil.Instance = new FileUtil();
        }
        return FileUtil.Instance;
    }

    public String getFilesPath() {
        String externalPath = this.ctx.getExternalFilesDir(null).getPath();
        return externalPath;
    }


    public String readFromFile(String dir, String filename) {
        try {
            String targetFolder = getFilesPath() + "/" + dir;
            String fullfilename = targetFolder + "/" + filename;
            File file = new File(fullfilename);
            if (file.exists() == false) {
                return "";
            } else {
                String res = "";
                FileInputStream fin = new FileInputStream(file);
                int length = fin.available();
                byte[] buffer = new byte[length];
                fin.read(buffer);
                res = new String(buffer, "UTF-8");
                fin.close();

                return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public void writeToFile(String dir, String filename, String content) {
        try {
            String targetFolder = getFilesPath() + "/" + dir;
            File targetFolderFile = new File(targetFolder);
            if (targetFolderFile.exists() == false) {
                targetFolderFile.mkdir();
            }
            String fullfilename = targetFolder + "/" + filename;
            File file = new File(fullfilename);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(content.getBytes());
            outStream.close();
        } catch (Exception e) {
            Log.d("writeerror",e.getMessage());
            e.printStackTrace();
        }

    }

    public void downloadImage(String url) {
        try {
            if (url.indexOf("assets://") > -1) {
                return;
            }
            int lastdot = url.lastIndexOf(".");
            String fileext = url.substring(lastdot);
            String dir = "image";
            String targetFolder = getFilesPath() + "/" + dir;
            File targetFolderFile = new File(targetFolder);
            if (targetFolderFile.exists() == false) {
                targetFolderFile.mkdir();
            }
            String md5filename = targetFolder + "/" + Util.MD5(url) + "_b" + fileext;
            File md5file = new File(md5filename);
            if (md5file.exists()) {
                return;
            } else {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            File file = Glide.with(ctx).load(url).downloadOnly(1, 1).get();
                            Log.d("fileload", file.getPath());
                            FileInputStream fis = new FileInputStream(file);
                            writeToLocal(md5filename, fis);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadDataFromUrl(String url, DataHandle<String> ret){
        try {
            //Glide.with(ctx).load(url).into(img);
            int lastdot = url.lastIndexOf(".");
            String fileext = ".mp4";
//            Log.d("fileloadfileext", fileext);
//            if(fileext){
//                fileext=".mp4";
//            }
            String dir = "cache";
            String targetFolder = getFilesPath() + "/" + dir;
            File targetFolderFile = new File(targetFolder);
            if (targetFolderFile.exists() == false) {
                targetFolderFile.mkdir();
            }
            String md5filename = targetFolder + "/" + Util.MD5(url) + "_b" + fileext;
            File md5file = new File(md5filename);
            if (md5file.exists()) {
                ret.loadSuccess(md5filename);
            } else {

                Handler handler = new Handler() {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        ret.loadSuccess(md5filename);
                    }
                };
                Util.GetRedirectUrl(url,new Handler() {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        String realurl = (String) msg.obj;

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    File file = Glide.with(ctx).load(realurl).downloadOnly(1, 1).get();
                                    Log.d("fileload", file.getPath());
//                            Files.move(file.toPath(),new File(md5filename).getParentFile().toPath(),
//                                    StandardCopyOption.REPLACE_EXISTING);
                                    FileInputStream fis = new FileInputStream(file);
                                    writeToLocal(md5filename, fis);
                                    Message msg = new Message();
                                    handler.sendMessage(msg);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                });


            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadImageFromUrl(ImageView img, String url) {
        try {
            if (url.indexOf("assets://") > -1) {
                String asseturl = url.replace("assets://", "");
                InputStream ims = ctx.getAssets().open(asseturl);
                Drawable d = Drawable.createFromStream(ims, null);
                img.setImageDrawable(d);
                return;
            }
            //Glide.with(ctx).load(url).into(img);
            int lastdot = url.lastIndexOf(".");
            String fileext = url.substring(lastdot);
            String dir = "image";
            String targetFolder = getFilesPath() + "/" + dir;
            File targetFolderFile = new File(targetFolder);
            if (targetFolderFile.exists() == false) {
                targetFolderFile.mkdir();
            }
            if(fileext.length()>4){
                fileext=".png";
            }
            String md5filename = targetFolder + "/" + Util.MD5(url) + "_b" + fileext;
            File md5file = new File(md5filename);
            if (md5file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(md5filename);
                img.setImageBitmap(bitmap);
            } else {
                Handler handler = new Handler() {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        Bitmap bitmap = BitmapFactory.decodeFile(md5filename);
                        img.setImageBitmap(bitmap);
                    }
                };
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            File file = Glide.with(ctx).load(url).downloadOnly(1, 1).get();
                            Log.d("fileload", file.getPath());
//                            Files.move(file.toPath(),new File(md5filename).getParentFile().toPath(),
//                                    StandardCopyOption.REPLACE_EXISTING);
                            FileInputStream fis = new FileInputStream(file);
                            writeToLocal(md5filename, fis);
                            Message msg = new Message();
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void loadImageToBitmap(String url,DataHandle<Bitmap> ret) {
        try {
            //Glide.with(ctx).load(url).into(img);
            int lastdot = url.lastIndexOf(".");
            String fileext = url.substring(lastdot);
            String dir = "image";
            String targetFolder = getFilesPath() + "/" + dir;
            File targetFolderFile = new File(targetFolder);
            if (targetFolderFile.exists() == false) {
                targetFolderFile.mkdir();
            }
            if(fileext.length()>4){
                fileext=".png";
            }
            String md5filename = targetFolder + "/" + Util.MD5(url) + "_b" + fileext;
            File md5file = new File(md5filename);
            if (md5file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(md5filename);
                ret.loadSuccess(bitmap);
            } else {
                Handler handler = new Handler() {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        Bitmap bitmap = BitmapFactory.decodeFile(md5filename);
                        ret.loadSuccess(bitmap);
                    }
                };
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            File file = Glide.with(ctx).load(url).downloadOnly(1, 1).get();
                            Log.d("fileload", file.getPath());
//                            Files.move(file.toPath(),new File(md5filename).getParentFile().toPath(),
//                                    StandardCopyOption.REPLACE_EXISTING);
                            FileInputStream fis = new FileInputStream(file);
                            writeToLocal(md5filename, fis);
                            Message msg = new Message();
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void writeToLocal(String fullfilename, InputStream input)
            throws IOException {
        File file = new File(fullfilename);
        String destDirName = file.getParent();
        String fileName = file.getName();
        String dirFile = destDirName + "/" + fileName;
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(dirFile);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        input.close();
    }

    public boolean fileExists(String dir, String filename) {
        String targetFolder = getFilesPath() + "/" + dir;
        String fullfilename = targetFolder + "/" + filename;
        File f = new File(fullfilename);
        return f.exists();
    }

    public void deleteFile(String dir, String filename) {
        String targetFolder = getFilesPath() + "/" + dir;
        String fullfilename = targetFolder + "/" + filename;
        File f = new File(fullfilename);
        f.delete();
    }

    public File[] listFiles(String dir) {
        String targetFolder = getFilesPath() + "/" + dir;
        File targetFolderFile = new File(targetFolder);
        File[] files = targetFolderFile.listFiles();
        if (files == null) {
            return new File[]{};
        }
        return files;
    }

    public String readFromFile(File file) {
        try {
            if (file.exists() == false) {
                return "";
            } else {
                String res = "";
                FileInputStream fin = new FileInputStream(file);
                int length = fin.available();
                byte[] buffer = new byte[length];
                fin.read(buffer);
                res = new String(buffer, "UTF-8");
                fin.close();

                return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
