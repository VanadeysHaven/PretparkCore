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
- `pretparkcore.controlrides`
  - This permission is _supposed_ to be given to RideOP's and higher.
  - This permission grants the following things:
    - Players with this permission will be allowed to use the _Attractie Menu_ sign's.
    - Players with this permission have access to the `/control` command.
    - Players with this permission will get another item in their hotbar to control rides.
- `pretparkcore.staffbepunch`
  - This permission is _supposed_ to be given to all members's of staff.
  - Player with this permission can be punched by players with the gadget.
- `pretparkcore.clearchat`
  - This permission allows the player to clear the chat.
  - Players with this command will not have their chat cleared.
    
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
- `/listrides`
  - This command will list all registered rides in the database.
  - The rides will be showed in this format: `id - name - status - location(x,y,z)`
  - Command is **ONLY** available to OPs and has **NO** permission.
- `/changeride (id) (o/d/m)`
  - This will toggle the status of a ride.
  - This will **NOT** make it closed in the world. Only for the plugin. **ONLY USE THIS FOR DEBUGGING, CORRECTING OR IN A COMMANDBLOCK THAT IS CONNECTED TO THE OPENING AND CLOSING SQUENCE OF THE RIDE!**
  - Command is **ONLY** available to OPs and has **NO** permission.
- `/reloadrides`
  - This command will reload all rides from the database.
  - Command is **ONLY** available to OPs and has **NO** permission.
- `/listvars`
  - This command will show all SignLink variables registered by the plugin.
    - Only vars that are linked to a ride in the code are registered and logged.
  - Command is **ONLY** available to OPs and has **NO** permission.
- `/control (ride)`
  - This command will show the specified ride's 'remote control'. This will allow RideOP's to actually ride the ride and control it at the same time!
  - This command requires the `pretparkcore.controlrides` permission node!
- `/cc`
  - This command will clear the chat for all players that do NOT have the `pretparkcore.clearchat` permission!
  - This command requires the `pretparkcore.clearchat` permission node!


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


