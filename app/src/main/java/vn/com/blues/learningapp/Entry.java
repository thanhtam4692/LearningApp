package vn.com.blues.learningapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Blues on 5/25/15.
 */
public class Entry implements Parcelable {
    private String id;
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String theAnswer;

    public Entry(String id, String question, String answerA, String answerB, String answerC, String answerD, String theAnswer) {
        this.id = id;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.theAnswer = theAnswer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getTheAnswer() {
        return theAnswer;
    }

    public void setTheAnswer(String theAnswer) {
        this.theAnswer = theAnswer;
    }

    // Parcelling part
    public Entry(Parcel in){
        String[] data = new String[7];

        in.readStringArray(data);
        this.id = data[0];
        this.question = data[1];
        this.answerA = data[2];
        this.answerB = data[3];
        this.answerC = data[4];
        this.answerD = data[5];
        this.theAnswer = data[6];
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.id,
                this.question,
                this.answerA,
                this.answerB,
                this.answerC,
                this.answerD,
                this.theAnswer});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Entry createFromParcel(Parcel in) {
            return new Entry(in);
        }

        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };
}
