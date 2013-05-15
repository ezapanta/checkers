package me.desht.checkers.responses;

import me.desht.checkers.CheckersGame;
import me.desht.checkers.Messages;
import me.desht.dhutils.MiscUtil;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SwapResponse extends YesNoResponse {

	public SwapResponse(CheckersGame game, String offerer) {
		super(game, offerer);
	}

	@Override
	public void doResponse(final String offeree) {

		deferTask(Bukkit.getPlayer(offerer), new Runnable() {
			@Override
			public void run() {
				if (accepted) {
					game.alert(offerer, Messages.getString("Game.swapOfferAccepted", getPlayerName()));
					game.swapColours();
				} else {
					game.alert(offerer, Messages.getString("Game.swapOfferDeclined", getPlayerName()));
					Player player = Bukkit.getPlayer(offeree);
					if (player != null) {
						MiscUtil.statusMessage(player, Messages.getString("Game.youDeclinedSwapOffer"));
					}
				}
			}
		});
	}
}
