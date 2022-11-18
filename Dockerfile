FROM ivonet/payara:5.193.1
COPY ./artifact/EDSClient.war $DEPLOY_DIR
