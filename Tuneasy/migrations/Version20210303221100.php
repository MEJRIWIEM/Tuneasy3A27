<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210303221100 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->abortIf($this->connection->getDatabasePlatform()->getName() !== 'mysql', 'Migration can only be executed safely on \'mysql\'.');

        $this->addSql('ALTER TABLE logement MODIFY id_logement INT NOT NULL');
        $this->addSql('ALTER TABLE logement DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE logement CHANGE id_logement id INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE logement ADD PRIMARY KEY (id)');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C8495558ABF955 FOREIGN KEY (logement_id) REFERENCES logement (id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->abortIf($this->connection->getDatabasePlatform()->getName() !== 'mysql', 'Migration can only be executed safely on \'mysql\'.');

        $this->addSql('ALTER TABLE logement MODIFY id INT NOT NULL');
        $this->addSql('ALTER TABLE logement DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE logement CHANGE id id_logement INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE logement ADD PRIMARY KEY (id_logement)');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C8495558ABF955');
    }
}
