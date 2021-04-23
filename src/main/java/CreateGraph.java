import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.VCARD;

public class CreateGraph {
    static String personURI = "http://ycrathi.com/YogeshRathi";

    public static void main(String[] args) {

        // create an empty Model
        Model model = ModelFactory.createDefaultModel();

        // create the resource
        model.createResource(personURI)
                .addProperty(VCARD.FN, "Yogesh Rathi")
                .addProperty(VCARD.EMAIL, "ycrathi@gmail.com")
                .addProperty(VCARD.BDAY, "01-01-2000")
                .addProperty(VCARD.N,
                        model.createResource()
                                .addProperty(VCARD.Given, "Yogesh")
                                .addProperty(VCARD.Family, "Yogi"));

        //printModel(model);
        printRDFFormat(model);
        //printRDFFileFormat(model);
        //writeModelInXMLForm(model);

    }

    private static void writeModelInXMLForm(final Model model) {
        // write the model in XML form to a file
        model.write(System.out, "N-TRIPLE");
    }

    private static void printRDFFileFormat(final Model model) {
        // write the model in XML form to a file
        model.write(System.out, "RDF/XML-ABBREV");
    }

    private static void printRDFFormat(final Model model) {
        // write the model in XML form to a file
        model.write(System.out);
    }

    private static void printModel(final Model model) {
        StmtIterator iter = model.listStatements();
        // print out the predicate, subject and object of each statement

        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement(); // get next statement
            Resource subject = stmt.getSubject(); // get the subject
            Property predicate = stmt.getPredicate(); // get the predicate
            RDFNode object = stmt.getObject(); // get the object
            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.println(object.toString());
            } else {
                // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }
            System.out.println(" .");
        }
    }
}