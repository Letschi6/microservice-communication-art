package de.art.examples.mc.kafka.producer;

import de.art.examples.mc.kafka.producer.domain.Article;
import de.art.examples.mc.kafka.producer.domain.EAN;
import org.apache.avro.Schema;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.avro.reflect.ReflectDatumWriter;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.UUID;

public class ArticleToSchemaViaReflectionExample {
    public static void main(String[] args) {
        Schema schema = ReflectData.get().getSchema(Article.class);
        System.out.println(schema.toString(true));

        Article article = new Article();
        article.setId(UUID.randomUUID().toString());
        article.setName("Reflected Article");
        article.setEanList(new LinkedList<>());
        article.setPrice(BigDecimal.valueOf(0.99));
        EAN ean = new EAN();
        ean.setEan("1234567890123");
        article.getEanList().add(ean);


        writeArticleToFile(schema, article);
        readArticleFromFile();
    }

    private static void readArticleFromFile() {
        // read from an avro into our Reflected class
        // open a file of ReflectedCustomers
        try {
            System.out.println("Reading article-reflected.avro");
            File file = new File("article-reflected.avro");
            DatumReader<Article> reader = new ReflectDatumReader<>(Article.class);
            DataFileReader<Article> in = new DataFileReader<>(file, reader);

            // read ReflectedCustomers from the file & print them as JSON
            for (Article reflectedArticle : in) {
                System.out.println(reflectedArticle.getEanList().get(0).getEan());
            }
            // close the input file
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeArticleToFile(Schema schema, Article article) {
        // create a file of ReflectedCustomers
        try {
            System.out.println("Writing article-reflected.avro");
            File file = new File("article-reflected.avro");
            DatumWriter<Article> writer = new ReflectDatumWriter<>(Article.class);
            DataFileWriter<Article> out = new DataFileWriter<>(writer)
                    .setCodec(CodecFactory.deflateCodec(9))
                    .create(schema, file);

            out.append(article);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
