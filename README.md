# Play Clustered Chat

This is based on the Scala clustered chat example in the [Play Socket.IO library](https://github.com/playframework/play-socket.io), but built to deploy to OpenShift.

To deploy to OpenShift, configure your docker environment variables to deploy to OpenShift, then run:

```
sbt docker:publishLocal
```

Then run:

```
oc apply -f openshift-deploy.yaml
```

Finally expose the `play-clustered-chat` service, and then open it in a browser.
