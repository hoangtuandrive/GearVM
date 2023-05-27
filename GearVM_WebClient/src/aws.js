import AWS from "aws-sdk";

AWS.config.update({
  // accessKeyId: "AKIA3XURV5V43S6M4JER",
  // secretAccessKey: "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",
  accessKeyId: process.env.accessKeyId,
  secretAccessKey: process.env.secretAccessKey,
  region: "ap-southeast-1",
});

export const s3 = new AWS.S3();
