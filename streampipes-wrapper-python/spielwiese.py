from streampipes.declarer import DeclarerSingleton
from streampipes.model.pipeline_element_config import Config
from streampipes.core import EventProcessor
from streampipes.submitter import StandaloneModelSubmitter


class HelloWorldProcessor(EventProcessor):

    def on_invocation(self):
        pass

    def on_event(self, event):
        event['greeting'] = 'hello world'
        return event

    def on_detach(self):
        pass


def main():
    # Configurations to be stored in key-value store (consul)
    config = Config(app_id='pe/org.apache.streampipes.processor.python')

    config.register(type='host',
                    env_key='SP_HOST',
                    default='localhost',
                    description='processor hostname')

    config.register(type='port',
                    env_key='SP_PORT',
                    default=8090,
                    description='processor port')

    config.register(type='service',
                    env_key='SP_SERVICE_NAME',
                    default='Python Processor',
                    description='processor service name')

    processors = {
        'org.apache.streampipes.processors.python.helloworld': HelloWorldProcessor,
    }

    # Declarer
    # add the dict of processors to the Declarer
    # This is an abstract class that holds the specified processors
    DeclarerSingleton.add(processors=processors)

    # StandaloneModelSubmitter
    # Initializes the REST api
    StandaloneModelSubmitter.init(config=config)


if __name__ == '__main__':
    main()