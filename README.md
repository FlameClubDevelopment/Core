# Disqualified (1.7 - 1.21)

Disqualified is a Minecraft plugin designed to enhance server functionality with various features including chat management, social media integration, and link restrictions. This repository contains the source code and configuration files needed to run the plugin.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Chat Management**: Manage chat settings and commands.
- **Social Media Integration**: Quick access to server's social media links.
- **Link Restriction**: Prevents players from sending links in chat.
- **Sound Notifications**: Plays sounds for various events.

## Installation

1. **Clone the Repository**:
    ```sh
    git clone https://github.com/FlameClubDevelopment/Disqualified.git
    ```

2. **Build the Plugin**:
    Navigate to the project directory and build the project using Maven:
    ```sh
    cd Disqualified
    mvn clean install
    ```

3. **Deploy the Plugin**:
    Copy the generated JAR file from the `target` directory to your server's `plugins` directory.

## Configuration

Configure the plugin by editing the `config.yml` file in the `plugins/Disqualified` directory. Set your server name, social media links, and other settings as needed.

## Usage

### Chat Management

Commands for managing chat settings and restrictions can be found in the `club.flame.disqualified.command.chat` package.

### Social Media Links

Social media links are configurable and can be accessed via specific commands.

### Link Restriction

The plugin prevents players from sending links in chat by default. You can customize the message and behavior in the configuration file.
