pipeline {
  agent {
    kubernetes {
      yamlFile 'src/main/pipeline/pod.yaml'
    }
  }
  stages {
    stage('Test Java') {
      steps {
        container('buildpipeline') {
          sh 'gradle test'
        }
      }
    }
    stage('Build Native') {
      steps {
        container('buildpipeline') {
          sh 'gradle buildNative'
        }
      }
    }
    stage('Build & Push Docker') {
      steps {
        container('buildpipeline') {
          sh 'skaffold build'
        }
      }
    }
    stage('Deploy') {
      steps {
        container('buildpipeline') {
          sh 'skaffold deploy'
        }
      }
    }
  }
}