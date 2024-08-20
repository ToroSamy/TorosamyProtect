## Dependency
- Residence
- TorosTorosamyCoreamy
## Usage
1. download [Residence](https://www.zrips.net/residence/) and [TorosamyCore](https://github.com/ToroSamy/TorosamyCore) as a dependency plugin
2. put the **dependencies** and this plugin into your plugins folder and start your server
3. Modify the default configuration file generated and restart your server
## Permission
- - **Usage:** /tsp reload
  - **Description:** reload this plugin
  - **Permission:** torosamyProtect.reload


## Config

### config.yml
```yml
enabled-worlds:
  - world
  - world_nether
  - world_the_end
default-world-config:
  #耕地保护
  farm-protection: true
  #阻止更改刷怪笼类型
  prevent-change-spawner: true
  #爆炸保护
  #如果该位置创建了领地 插件则会不会监听
  #该位置是否允许爆炸由/res set处理
  explosion-protect: true
  #阻止交互容器
  prevent-interact-container: false
  #阻止放置方块
  prevent-place: false
  #阻止破坏方块
  prevent-break: false
  #阻止火焰蔓延
  prevent-fire-spread: true
  #是否开启死亡不掉落
  keep-inventory: true

```

### lang.yml
```yml
reload-message: "&b[服务器娘]&a插件 &eTorosamyProtect &a重载成功喵~"
```

## Contact Author

- qq: 1364596766
- website: https://www.torosamy.net

## License

[MIT](./LICENSE)
