package com.example.sqs.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class SqsMessage {

    @SerializedName("Type")
    public String type;

    @SerializedName("MessageId")
    public String messageId;

    @SerializedName("TopicArn")
    public String topicArn;

    @SerializedName("Message")
    public String message;

    @SerializedName("Timestamp")
    public Date timestamp;

    @SerializedName("SignatureVersion")
    public String signatureVersion;

    @SerializedName("Signature")
    public String signature;

    @SerializedName("SigningCertURL")
    public String signingCertURL;

    @SerializedName("UnsubscribeURL")
    public String unsubscribeURL;

}
