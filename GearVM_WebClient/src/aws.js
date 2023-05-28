import AWS from "aws-sdk";

AWS.config.update({
  accessKeyId: process.env.REACT_APP_AWS_ACCESS_KEY_ID,
  secretAccessKey: process.env.REACT_APP_SECRET_ACCESS_KEY,
  region: "ap-southeast-1",
});

export const s3 = new AWS.S3();
