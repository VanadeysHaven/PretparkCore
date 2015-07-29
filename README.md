# **PretparkCore** _by Cooltimmetje_
> **NOTE THE FOLLOWING** This plugin is _Dutch_, but the documentation is in _English_.

## Permissions
> All permissions are defaulted to OPs, and are ALWAYS available for them.

- `pretparkcore.bypassgm`
  - This permission is _supposed_ to be given to builders and higher.
  - This permission grants the following things:
    - Players with this permission will be given `gamemode 1` on join.
      - Players that do **NOT** have this permisson will be given `gamemode 2` on join!
    - Players with this permission have access to the `/resetinv` command.
    - Players with this permission can edit their inventory, pickup and drop items.
    - Players that have this permission will not have their inventory reset on join.
      - Other players will have their inventory resetted.
- `pretparkcore.coins.other`
  - This permission will grant the ability to see the coins of other players.
    - To do this you can use the `/coins [player]` command.
    - If you don't specify a player you will be defaulted to yourself.
    - Players that do **NOT** have this permission will always be defaulted to their selves.

## Commands
> Arguments: (required) [optional]

- `/fixgamemodes`
  - This command will fix the gamemode of **ALL** players on the server, using the `pretparkcore.bypassgm` permission node.
  - Command is **ONLY** available to OPs and has **NO** permission.
- `/coins [player]`
  - This command will show your coins.
  - When you specify a player, you see their coins.
    - This requires the `pretparkcore.coins.other` permission node!
    - When you have the permission and you do not specify a player you will be defaulted to yourself.
    - When you don't have the permission you will always be defaulted to yourself, even if you specify a player.
- `/resetinv`
  - This will reset your inventory to the default inventory.
  - Requires the `pretparkcore.bypassgm` permission node.
- `/masscoins (amount)`
  - This will give all online players the specified amount of coins.
  - Command is **ONLY** available to OPs and has **NO** permission.
- `/givecoins (player) (amount)`
  - This will give the specified player the specified amount of coins.
  - Command is **ONLY** available to OPs and has **NO** permission.
- `/takecoins (player) (amount)`
  - This will take the specified amount of coins from the specified player.
  - Command is **ONLY** available to OPs and has **NO** permission.
- `/setcoins (player) (amount)`
   - This will set the specified amount of coins to the specified player.
   - Command is **ONLY** available to OPs and has **NO** permission.

## Configuration File
> The config showed here is the default config.

```
Database:
    serverName: localhost --Enter the ip or host of your Mysql database here.
    port: 3306 --Enter the port of your Mysql database here. (if you don't know the port leave it on 3306)
    databaseName: database --Enter the database name that the plugin should use here.
    user: root --Enter the username of the account the plugin can use to connect.
    password: SwaggyPasswordOfDoom --Enter the password that goes with the username.
Globaldata:
    uniqueusers: 0 --This is the amount of unique players, you dont need to edit it, but you can if you wish.
```


