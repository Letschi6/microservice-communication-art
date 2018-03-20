package de.art.examples.mc.kafka.producer;

import de.art.examples.mc.kafka.producer.domain.ArticleAvro;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by redmann on 08.04.16.
 */

public class ArticleAvroReadWriteExample {

    public static void main(String[] args) {
        final ArticleAvro.Builder articleBuilder = ArticleAvro.newBuilder();
        articleBuilder.setId(UUID.randomUUID().toString());
        articleBuilder.setName("Article 1");
        articleBuilder.setPrice(BigDecimal.valueOf(1.99));
        final ArticleAvro articleAvro = articleBuilder.build();
        System.out.println(String.valueOf(articleAvro));


        // write it out to a file
        final DatumWriter<ArticleAvro> datumWriter = new SpecificDatumWriter<>(ArticleAvro.class);

        try (DataFileWriter<ArticleAvro> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(articleAvro.getSchema(), new File("article-avro.avro"));
            dataFileWriter.append(articleAvro);
            System.out.println("Wrote article to article-avro.avro");
        } catch (IOException e) {
            e.printStackTrace();
        }


        // read it from a file
        final File file = new File("article-avro.avro");
        final DatumReader<ArticleAvro> datumReader = new SpecificDatumReader<>(ArticleAvro.class);
        final DataFileReader<ArticleAvro> dataFileReader;
        try {
            System.out.println("Reading record");
            dataFileReader = new DataFileReader<>(file, datumReader);
            while (dataFileReader.hasNext()) {
                ArticleAvro readArticle = dataFileReader.next();
                System.out.println(readArticle.toString());
                System.out.println("Article name: " + readArticle.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
