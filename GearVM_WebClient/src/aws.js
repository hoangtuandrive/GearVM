import AWS from "aws-sdk";

AWS.config.update({
  accessKeyId: process.env.accessKeyId,
  secretAccessKey: process.env.secretAccessKey,
  region: "ap-southeast-1",
});

export const s3 = new AWS.S3();
