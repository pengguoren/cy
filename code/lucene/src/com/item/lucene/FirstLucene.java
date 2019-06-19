package com.item.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;

/**
 * 入门
 * 创建索引
 * 查询索引
 */
public class FirstLucene {

    @Test
    public void testIndex() throws Exception{
//        第一步：创建一个java工程，并导入jar 包
//        第二步：创建一个indexwriter对象。
        String path = "H:\\temp\\index";
//        1) 指定索引库的存放位置Directory对象
        Directory directory = FSDirectory.open(Paths.get(path));
//        2) 指定一个分析器，对文档内容进行分析
        Analyzer analyzer = new StandardAnalyzer();//官方推荐
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);


        File f = new File("C:\\Users\\19112\\Desktop\\新建文件夹");
        File[] files = f.listFiles();
        for (File file:files) {
            //        第二步：创建document对象
            Document document = new Document();
            //文件名称
            String file_name = file.getName();
            Field fileNameField = new TextField("fileName",file_name, Field.Store.YES);
            //文件大小
            long file_size = FileUtils.sizeOf(file);
            Field fileSizeField = new LongPoint("fileSize", file_size);
            //文件路径
            String file_path = file.getPath();
            Field filePathField = new StoredField("filePath",file_path);
            //文件内容
            String file_content = FileUtils.readFileToString(file,"UTF-8");
            Field fileContentField = new TextField("fileContent",file_content, Field.Store.NO);


//        第三步：创建field对象，将field添加到document对象中
            document.add(fileNameField);
            document.add(fileSizeField);
            document.add(filePathField);
            document.add(fileContentField);
            //        第四步：使用indexwriter对象将document对象写人索引库，此过程进行索引创建并将索
//            引和document对象写入索引库
            indexWriter.addDocument(document);
        }
//        第五步：关闭IndexWriter对象
            indexWriter.close();

    }

    @Test
    public void testSerch() throws Exception{
 //        实现步骤
//        第一步:创建一个 Directory对象,也就是索引库存放的位置。
        String path = "H:\\temp\\index";
        Directory directory = FSDirectory.open(Paths.get(path));
//        第二步:创建一个 indexReader对象,需要指定 Directory对象。
        IndexReader indexReader = DirectoryReader.open(directory);
//        第三步:创建一个 indexsearcher对象,需要指定 IndexReader对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//        第四步:创建一个 TermQuery对象,指定查询的域和查询的关键词。
        Query query = new TermQuery(new Term("fileName","自"));
//        第五步:执行查询
        TopDocs topDocs = indexSearcher.search(query, 3);
//        第六步:返回查询结果。遍历查询结果并输出。
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            Document doc = indexSearcher.doc(scoreDoc.doc);
//            文件名称。
            String fileName = doc.get("fileName");
            System.out.println(fileName);
//            文件内容。
            String fileContent = doc.get("fileContent");
            System.out.println(fileContent);
//            文件大小。
            String fileSize = doc.get("fileSize");
            System.out.println(fileSize);
//            文件路径。
            String filePath = doc.get("filePath");
            System.out.println(filePath);
        }
//        第七步:关闭 IndexReader对象
        indexReader.close();
    }

    public IndexSearcher getIndexsercher() throws  Exception{
        String path = "H:\\temp\\index";
        Directory directory = FSDirectory.open(Paths.get(path));
        IndexReader indexReader = DirectoryReader.open(directory);
        return new IndexSearcher(indexReader);
    }

    public IndexWriter getIndexWriter() throws  Exception{
        String path = "H:\\temp\\index";
        Directory directory = FSDirectory.open(Paths.get(path));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        return new IndexWriter(directory,indexWriterConfig);
    }
    @Test
    public void testDelete() throws Exception{
        IndexWriter indexWriter = getIndexWriter();
        indexWriter.deleteAll();
        indexWriter.close();
    }

    @Test
    public void testDeletePart() throws  Exception{
        IndexWriter indexWriter = getIndexWriter();
        Query query = new TermQuery(new Term("fileName","自"));
        indexWriter.deleteDocuments(query);
        indexWriter.close();
    }

    @Test
    public void testupdate() throws Exception{
        IndexWriter indexWriter = getIndexWriter();
        Document document = new Document();
        document.add(new TextField("FieldN","测试文件名",Field.Store.YES));
        document.add(new TextField("Fieldc","测试文件内容",Field.Store.YES));
        indexWriter.updateDocument(new Term("fileName","程"),document);
    }
}
