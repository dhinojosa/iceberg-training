package com.evolutionnext;

import org.apache.hadoop.conf.Configuration;
import org.apache.iceberg.PartitionSpec;
import org.apache.iceberg.Schema;
import org.apache.iceberg.Table;
import org.apache.iceberg.catalog.Namespace;
import org.apache.iceberg.catalog.TableIdentifier;
import org.apache.iceberg.hadoop.HadoopCatalog;
import org.apache.iceberg.types.Types;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class SampleReader {
    public static void main(String[] args) {
        Configuration conf = getConfiguration();

        String warehousePath = "s3a://warehouse/iceberg/";

        Namespace ns = Namespace.of("demo");
        TableIdentifier id = TableIdentifier.of(ns, "weather");

        // Schema: city string, state string, temperature_celcius number, dew_point number
        Schema schema = new Schema(
            Types.NestedField.required(1, "city", Types.StringType.get()),
            Types.NestedField.required(2, "state", Types.StringType.get()),
            Types.NestedField.optional(3, "temperature_celcius", Types.DoubleType.get()),
            Types.NestedField.optional(4, "dew_point", Types.DoubleType.get())
        );

        PartitionSpec spec = PartitionSpec.unpartitioned();

        try (HadoopCatalog catalog = new HadoopCatalog(conf, warehousePath)) {
            try {
                catalog.createNamespace(ns);
                System.out.println("Created namespace: " + ns);
            } catch (org.apache.iceberg.exceptions.AlreadyExistsException ignore) {
                // ok
            }

            Table table;
            if (!catalog.tableExists(id)) {
                table = catalog.createTable(id, schema, spec);
                System.out.println("Created table: " + id);
            } else {
                table = catalog.loadTable(id);
                System.out.println("Loaded existing table: " + id);
            }

            System.out.println("Table location: " + table.location());
            System.out.printf("Table Exists? %b%n", catalog.tableExists(id));

            System.out.println("Namespaces:");
            catalog.listNamespaces().forEach(n -> System.out.println("- " + n));

            System.out.println("Tables in 'demo':");
            catalog.listTables(ns).forEach(t -> System.out.println("- " + t));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static @NotNull Configuration getConfiguration() {
        Configuration conf = new Configuration();
        conf.set("fs.s3a.endpoint", "http://localhost:9000");
        conf.set("fs.s3a.access.key", "minioadmin");
        conf.set("fs.s3a.secret.key", "minioadmin");
        conf.setBoolean("fs.s3a.path.style.access", true);
        conf.setBoolean("fs.s3a.connection.ssl.enabled", false);
        conf.set("fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem");
        return conf;
    }
}
