pipelineJob('run_taf'){
  description('example pipeline for testing')
  environmentVariables {
  env('TAF_VERSION', '0.0.1')
  }
  parameters {
    stringParam('TAF_version', defaultValue = 'master', \
    description = 'choose branch/version for TAF')

    choiceParam('environament', ['lab1', 'l1b5a', 'labx', 'pre_prod', 'prod'], \
    description = 'choose environament for testing ')
  }
  triggers{
    scm("H/45 * * * *")
  }
    definition {
    cps {
      sandbox()
      script('''
      def parallel_jobs = [:]
      def parallel_items
      pipeline {
        agent any

        stages {
            stage('test') {
              steps {
                  sh 'Echo hello ${params.environment}'
                }
            }
        }'''.stripIndent())
    }
    }
}