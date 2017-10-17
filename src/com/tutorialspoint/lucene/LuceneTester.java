package com.tutorialspoint.lucene;
import java.io.IOException;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
public class LuceneTester {
	String indexDir ;
	String dataDir ;
	Indexer indexer;
	Searcher searcher;
        String resultTester="";
        /*
	public static void main(String[] args) {
		LuceneTester tester;
		try {
			tester = new LuceneTester();
			tester.createIndex();
			tester.search("Mohan");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
*/
	public void createIndex() throws IOException{
		indexer = new Indexer(indexDir);
		int numIndexed;
		long startTime = System.currentTimeMillis();
		numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
		long endTime = System.currentTimeMillis();
                 this.resultTester += indexer.indexerLogs;
		indexer.close();
		//System .out.println(numIndexed+" File indexed, time taken: "+(endTime-startTime)+" m s");
                this.resultTester += numIndexed+" File indexed, time taken: "+(endTime-startTime)+" m s\n";
	}
	public void search(String searchQuery) throws IOException, ParseException{
		searcher = new Searcher(indexDir);
		long startTime = System.currentTimeMillis();
		TopDocs hits = searcher.search(searchQuery);
		long endTime = System.currentTimeMillis();
		//System.out.println(hits.totalHits +" documents found.Time :" + (endTime - startTime));
                this.resultTester += hits.totalHits +" documents found.Time :" + (endTime - startTime)+"\n";
		for(ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			//System .out.println("File: " + doc.get(LuceneConstants.FILE_PATH));
                        this.resultTester += "File: " + doc.get(LuceneConstants.FILE_PATH)+"\n";
		}
		searcher.close();
	}
        public String getResultTester(){
        return  this.resultTester ;
        }
}
