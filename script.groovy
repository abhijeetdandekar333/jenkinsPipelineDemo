def buildApp() {
  echo 'Building the application...'
}
def testApp() {
  echo 'testing the application...'
}
def deployApp() {
  echo 'Deploying the application...'
  echo "Deploying version ${params.VERSION}"
}
return this
