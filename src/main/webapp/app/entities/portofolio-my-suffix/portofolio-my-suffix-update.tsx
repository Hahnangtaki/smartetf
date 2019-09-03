import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './portofolio-my-suffix.reducer';
import { IPortofolioMySuffix } from 'app/shared/model/portofolio-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPortofolioMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPortofolioMySuffixUpdateState {
  isNew: boolean;
}

export class PortofolioMySuffixUpdate extends React.Component<IPortofolioMySuffixUpdateProps, IPortofolioMySuffixUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { portofolioEntity } = this.props;
      const entity = {
        ...portofolioEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/portofolio-my-suffix');
  };

  render() {
    const { portofolioEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.portofolio.home.createOrEditLabel">Create or edit a Portofolio</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : portofolioEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="portofolio-my-suffix-id">ID</Label>
                    <AvInput id="portofolio-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="customerIdLabel" for="portofolio-my-suffix-customerId">
                    Customer Id
                  </Label>
                  <AvField id="portofolio-my-suffix-customerId" type="string" className="form-control" name="customerId" />
                </AvGroup>
                <AvGroup>
                  <Label id="productIdLabel" for="portofolio-my-suffix-productId">
                    Product Id
                  </Label>
                  <AvField id="portofolio-my-suffix-productId" type="string" className="form-control" name="productId" />
                </AvGroup>
                <AvGroup>
                  <Label id="portofolioDateLabel" for="portofolio-my-suffix-portofolioDate">
                    Portofolio Date
                  </Label>
                  <AvField id="portofolio-my-suffix-portofolioDate" type="date" className="form-control" name="portofolioDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="unitLabel" for="portofolio-my-suffix-unit">
                    Unit
                  </Label>
                  <AvField id="portofolio-my-suffix-unit" type="string" className="form-control" name="unit" />
                </AvGroup>
                <AvGroup>
                  <Label id="avgPriceLabel" for="portofolio-my-suffix-avgPrice">
                    Avg Price
                  </Label>
                  <AvField id="portofolio-my-suffix-avgPrice" type="string" className="form-control" name="avgPrice" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/portofolio-my-suffix" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  portofolioEntity: storeState.portofolio.entity,
  loading: storeState.portofolio.loading,
  updating: storeState.portofolio.updating,
  updateSuccess: storeState.portofolio.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PortofolioMySuffixUpdate);
