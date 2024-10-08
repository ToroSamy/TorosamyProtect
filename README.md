## Dependency
- Residence[soft]
- TorosamyCore
## Function
- When there is Residence, the Residence takes over and explodes
- Basic protection of world management
## FuturePlans
- Add a ban on custom original items
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
#打印爆炸的实体 和 伤害载具的实体
debug: false
default-world-config:
  #耕地保护
  farm-protection: true
  #阻止更改刷怪笼类型
  prevent-change-spawner: true
  #爆炸保护
  explosion-protect: true
  #阻止交互容器
  prevent-interact-container: false
  #忽略箱子和潜影箱
  ignore-chest: false
  #阻止放置方块
  prevent-place: false
  #阻止破坏方块
  prevent-break: false
  #阻止火焰蔓延
  prevent-fire-spread: true
  #是否开启死亡不掉落
  keep-inventory: true
  #是否阻止幽匿催发体蔓延
  prevent-sculk-catalyst: true

```

### lang.yml
```yml
reload-message: "&b[服务器娘]&a插件 &eTorosamyProtect &a重载成功喵~"
```

## Contact Author

- qq: 1364596766
- website: https://www.torosamy.net

## License

[GPL-3.0 license](./LICENSE)
