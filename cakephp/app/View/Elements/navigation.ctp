<!-- main navigation for big devices -->
<ul>
	<?php
		foreach ( $navarray as $mainnav ) {
	?>
		<li><?php echo $this->Html->link($mainnav['title'], $mainnav['routing']); ?>
			<?php
				if ( $mainnav['subnav'] ) {
			?>
				<ul>
					<?php
						foreach ( $mainnav['subnav'] as $subnav ) {
					?>
						<li><?php echo $this->Html->link($subnav['title'], $subnav['routing']); ?></li>
					<?php
						}
					?>
				</ul>
			<?php
				}
			?>
		</li>
	<?php
		}
	?>
</ul>

<!-- main navigation for small devices -->
<select onchange="if ( this.value ) window.location.href=this.value">
	<option selected="selected" value="">Go to...</option>
	<?php
		foreach ( $navarray as $mainnav ) {
	?>
		<option value="<?php echo Router::url($mainnav['routing']); ?>"><?php echo $mainnav['title']; ?></option>
			<?php
				foreach ( $mainnav['subnav'] as $subnav ) {
			?>
				<option value="<?php echo Router::url($subnav['routing']); ?>">- <?php echo $subnav['title']; ?></option>
			<?php
				}
			?>
	<?php
		}
	?>
</select>

