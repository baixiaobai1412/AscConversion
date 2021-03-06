package ord.AscConversion;


public class ConvertAndCompress {
    public static void GenerateToPDF(long  time)  {
        //创建一个存放pdf的文件的代码
        String MkdirPDF = String.format("mkdir -p generate/PDF/%s", time);
        //生成pdf文件的代码
        String GenerateToPDF = String.format("bundle exec asciidoctor-pdf -r asciidoctor-diagram -r asciidoctor-pdf-cjk-kai_gen_gothic -a pdf-style=resources/CN-theme.yml -a pdf-fontsdir=resources/fonts seia.asc -o generate/PDF/%s/MyDocumentation.pdf --trace", time); //生成pdf文件的命令
        //通过7z打包pdf文件的代码
        String Compress = String.format("7z a ./generate/PDF/%s/MyPDF.7z ./generate/PDF/%s/MyDocumentation.pdf ", time,time);
        //创建一个存放pdf的dir
        SystemOperate.CallSystem(MkdirPDF);
        //生成pdf文件
        SystemOperate.CallSystem(GenerateToPDF);
        //通过7z打包pdf文件
        SystemOperate.CallSystem(Compress);
    }

    public static void GenerateToHTML(long  time)  {
        String MkdirHTML = String.format("mkdir -p generate/HTML/%s", time);
        String GenerateToHTML = String.format("bundle exec asciidoctor -r asciidoctor-diagram seia.asc -o generate/HTML/%s/MyDocumentation.html", time); //生成HTML文件
        String Compress = String.format("7z a ./generate/HTML/%s/MyHTML.7z  ./generate/HTML/%s/MyDocumentation.html", time , time);
        SystemOperate.CallSystem(MkdirHTML);
        SystemOperate.CallSystem(GenerateToHTML);
        SystemOperate.CallSystem(Compress);
    }

    public static void GenerateToASC(long  time)  {
        String MkdirASC = String.format("mkdir -p generate/ASC/%s", time);
        String Compress = String.format("7z a ./generate/ASC/%s/MyASC.7z ./seia.asc",time);
        SystemOperate.CallSystem(MkdirASC);
        SystemOperate.CallSystem(Compress);
    }
}
