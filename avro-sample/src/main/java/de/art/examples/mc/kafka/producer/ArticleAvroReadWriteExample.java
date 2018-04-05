package de.art.examples.mc.kafka.producer;

import de.art.examples.mc.kafka.producer.domain.ArticleAvro;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
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
        writeSpecificDataToFile();
        readSpecificDataFromFile();
        readGenericDataFromFile();


    }

    private static void writeSpecificDataToFile() {
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
    }

    private static void readSpecificDataFromFile() {
        // read it from a file
        final File file = new File("article-avro.avro");
        final DatumReader<ArticleAvro> datumReader = new SpecificDatumReader<>(ArticleAvro.class);
        try {
            System.out.println("\nReading specific record");
            final DataFileReader<ArticleAvro> dataFileReader = new DataFileReader<>(file, datumReader);
            while (dataFileReader.hasNext()) {
                ArticleAvro readArticle = dataFileReader.next();
                System.out.println(readArticle.toString());
                System.out.println("Article name: " + readArticle.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readGenericDataFromFile() {
        // read generic it from a file
        final File file = new File("article-avro.avro");
        final DatumReader<GenericRecord> genericDatumReader = new GenericDatumReader<>();
        try (DataFileReader<GenericRecord> genericRecordDataFileReader = new DataFileReader<>(file, genericDatumReader)) {
            GenericRecord genericArticleRead = genericRecordDataFileReader.next();
            System.out.println("\nRead generic record");
            System.out.println(genericArticleRead.toString());

            // get the data from the generic record
            System.out.println("First name: " + genericArticleRead.get("name"));

            // read a non existent field
            System.out.println("Non existent field: " + genericArticleRead.get("not_here"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
