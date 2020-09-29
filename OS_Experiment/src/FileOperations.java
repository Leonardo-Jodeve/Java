import java.io.*;
import java.util.Arrays;

public class FileOperations
{
    public static void readFrom(String source_filepath, int[] container)
    {
        try
        {
            int i=0;
            InputStream in = new FileInputStream(source_filepath);
            DataInputStream datain = new DataInputStream(in);
            while (true)
            {
                try
                {
                    container[i]=datain.readInt();
                    i++;
                }
                catch (EOFException e)
                {
                    break;
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("数组不够大！");
                    break;
                }
            }
            datain.close();
            in.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("文件不存在！");
        }
        catch (IOException e)
        {
            System.out.println("文件格式有误！");
        }
    }

    public static void writeTo(String target_filepath, int[] source)
    {
        try
        {
            OutputStream out = new FileOutputStream(target_filepath);
            DataOutputStream dataout = new DataOutputStream(out);
            for(int i=0; i<source.length; i++)
            {
                dataout.writeInt(source[i]);
            }
            dataout.close();
            out.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("写入错误！");
        }
    }

    public static void copy(String source_filepath, String target_filepath)
    {
        File file = new File(target_filepath);
        if(!file.exists())
            file.mkdirs();
        String filename = source_filepath.substring(source_filepath.lastIndexOf("\\")+1);
        target_filepath = target_filepath + File.separator + filename;
        try
        {
            FileInputStream filein = new FileInputStream(source_filepath);
            FileOutputStream fileout = new FileOutputStream(target_filepath);
            byte datas[] = new byte[1024*64];
            int len = 0;
            while((len = filein.read(datas))!=-1)
            {
                fileout.write(datas,0,len);
            }
            filein.close();
            fileout.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("文件不存在！");
        }
        catch (IOException e)
        {
            System.out.println("读写错误！");
        }
    }

    public static void move(String source_filepath, String target_filepath)
    {
        File fold = new File(target_filepath);
        if(!fold.exists())
            fold.mkdirs();
        File file=new File(source_filepath);
        file.renameTo(new File(target_filepath+file.getName()));
    }

    public static void rename(String source_filepath, String new_name)
    {
        String filepath = source_filepath.substring(0,source_filepath.lastIndexOf("\\")+1);
        File oldfile = new File(source_filepath);
        File newfile = new File(filepath+new_name);
        oldfile.renameTo(newfile);
    }

    public static void delete(String source_filepath)
    {
        File folder = new File(source_filepath);
        delete(folder);
    }

    public static void delete(File folder)
    {
        File[] files = folder.listFiles();
        if(files != null)
        {
            for (File file : files)
            {
                if(file.isFile())
                    file.delete();
                else
                    delete(file);
            }
        }
        folder.delete();
    }

    public static void main(String[] args)
    {
        int[] data_source = RamPageReplace.getRandomArray(16,0,100);
        System.out.println("当前生成的数组："+DiskAccess.printArray(data_source));

        writeTo("E:\\array.txt", data_source);
        System.out.println("将数组数据写入文件 E:\\array.txt 中");

        Arrays.fill(data_source, 0);
        System.out.println("数组填0："+DiskAccess.printArray(data_source));

        readFrom("E:\\array.txt", data_source);
        System.out.println("从文件读取原数组："+DiskAccess.printArray(data_source));

        copy("E:\\array.txt","E:\\ArrayOut\\English");
        System.out.println("将 E:\\array.txt 拷贝到 E:\\ArrayOut\\English 下");

        move("E:\\array.txt","E:\\ArrayOut\\Chinese\\");
        System.out.println("将 E:\\array.txt 剪切到 E:\\ArrayOut\\Chinese 下");

        rename("E:\\ArrayOut\\Chinese\\array.txt","数组.txt");
        System.out.println("将 E:\\ArrayOut\\Chinese\\array.txt 改名为 数组.txt");

        delete("E:\\ArrayOut\\cache");
        System.out.println("已经删除缓存文件");
    }
}
